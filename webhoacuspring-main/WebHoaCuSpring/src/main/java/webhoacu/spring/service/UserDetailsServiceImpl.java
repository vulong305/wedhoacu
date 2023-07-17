package webhoacu.spring.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

import webhoacu.spring.model.MyUserDetails;
import webhoacu.spring.model.NhanVien;
import webhoacu.spring.repository.NhanVienRepository;
 
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private NhanVienRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
//		NhanVien user = userRepository.findByEmail(username);
        NhanVien user = userRepository.findByTaiKhoan(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
 
}
