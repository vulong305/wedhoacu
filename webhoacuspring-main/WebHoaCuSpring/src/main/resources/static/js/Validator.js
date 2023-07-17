
// Object Validator
function Validator(options){
    var formElement = document.querySelector(options.form);
    var selectorRules = {}
    //lấy đúng thẻ cha của element để lấy được form-message
    function getParent(element,selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)){
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }
    // hàm thêm invalid nếu chưa nhập
    function Validate(inputElement, rule){
        var errorElement = getParent(inputElement,options.formGroup).querySelector(options.errorSelector);
        var errorMessage;
        //selector.rule.[selector của đang kiểm tra]
        var rules = selectorRules[rule.selector]

        //kiểm tra qua từng rule[]
        //nếu rule[i] có lỗi thì dừng, không kiểm tra các rule[i+1] tiếp theo
        for(let i in rules){
            errorMessage = rules[i](inputElement.value);
            if (errorMessage) break;
        }
        if (errorMessage) {
            errorElement.innerText = errorMessage;
            getParent(inputElement,options.formGroup).classList.add('invalid')
        } else {
            errorElement.innerText = '';
            getParent(inputElement,options.formGroup).classList.remove('invalid')
            }
        return !errorMessage; // có lỗi thì trả về false, ko thì true
    }
// có lỗi: return false
    //lấy element của form 
    if(formElement) {
        var isFormValid = true;
        //khi submit form
        formElement.onsubmit = function (e){
            e.preventDefault();
            options.rules.forEach(function (rule) {
                var inputElement = formElement.querySelector(rule.selector);
                var isValid = Validate(inputElement,rule);
                if (!isValid){
                    isFormValid = false;
                }   
            });
        // Nếu ko có lỗi
            if (isFormValid) {
                if (typeof options.onSubmit === 'function') {
                //lấy tất cả thẻ có attribute là name và ko có attribute disabled
                var enableInputs = formElement.querySelectorAll('[name]:not([disabled])') ;
                var formValues = Array.from(enableInputs).reduce(function(values,input){
                    values[input.name] = input.value //thêm một trường input.name vào object
                    return values ; 
                },{})
                options.onSubmit({
                    formValues
                })
            }
            } 
             
        }
        options.rules.forEach(function (rule) {

            //lưu lại các rule trong mỗi input
           if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test)
           } else {
                selectorRules[rule.selector] = [rule.test];
           }
           
            var inputElement = formElement.querySelector(rule.selector);
            var errorElement = getParent(inputElement,options.formGroup).querySelector(options.errorSelector);
            //xử lí trường hợp blur ra ngoài input
            if (inputElement) {
                inputElement.onblur = function(){
                    Validate(inputElement,rule);
                }
            }

            //xử lí trường hợp đang nhập
                inputElement.oninput = function() {
                    errorElement.innerText = '';
                    getParent(inputElement,options.formGroup).classList.remove('invalid')
                }
                
            })
        
    //duyệt qua mảng showPass, lấy hàm Validator.showPass
    //hàm trên trả về 1 object
    //chạy các lệnh dưới với dữ liệu là object trả về
    options.showPass.forEach(function(show){ 
        if (show){
            checkElement = document.querySelector(show.checkELement);
            checkElement.onchange = function(e){
                if (e.target.checked) {
                    formElement.querySelector(show.selector1).setAttribute('type','text');
                    formElement.querySelector(show.selector2).setAttribute('type','text');
                } else {
                    formElement.querySelector(show.selector1).setAttribute('type','password');
                    formElement.querySelector(show.selector2).setAttribute('type','password');
                }
            }
        }   

    })
    }
}



//Define rules
Validator.isRequired = function(selector,message){
    return {
        selector: selector,
        test: function (value){
            return value.trim() ? undefined : message || "Vui lòng nhập trường này !" //bỏ khoảng cách
        }
    }
}

Validator.isEmail = function(selector,message){
     return {
        selector: selector,
        test: function (value){
            var regex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
            return regex.test(value) ? undefined : message || 'Trường này phải là Email !' ;
        }
    }
}
Validator.minLength = function(selector,min,message){
    return {
       selector: selector,
       test: function (value){
           return value.length >= min ? undefined :  `Vui lòng nhập tối thiểu ${min} kí tự !`;
       }
   }
}

Validator.isPhone = function(selector,message){
    return {
       selector: selector,
       test: function (value){
           var regex = /(84|0[3|5|7|8|9])+([0-9]{8})\b/
           return regex.test(value) ? undefined : message ||'Số điện thoại không đúng !' ;
       }
   }
}

Validator.isConfirmed = function(selector,getCheck,message){
    return {
        selector: selector,
        test: function (value){
            return value === getCheck() ? undefined : message || 'Giá trị nhập không đúng'
        }
    }
}
//hiển thị mật khẩu
Validator.showPass = function(checkELement,selector1,selector2){
    return {
        checkELement: checkELement,
        selector1: selector1,
        selector2: selector2
    } 
}



