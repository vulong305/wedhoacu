package webhoacu.spring.model;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
public class MyUserDetails implements UserDetails {
	private NhanVien user;
    
    public MyUserDetails(NhanVien user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Quyen> quyens = user.getQuyens();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Quyen quyen: quyens) {
            authorities.add(new SimpleGrantedAuthority(quyen.getTenQuyen()));
        }
         
        return authorities;
    }
 
    @Override
//  Lấy mật khẩu trong Database để đăng nhập
    public String getPassword() {
        return user.getMatKhau();
    }
 
    @Override
//  Lấy email hoặc tài khoản trong Database để đăng nhập
    public String getUsername() {
        return user.getTaiKhoan();
    }
    
    public String getHoTen() {
        return user.getHoTen();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
}
