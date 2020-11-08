package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeUserDataAccessService implements UserDao {

    private final PasswordEncoder passwordEncoder;
    List<User> applicationUsers;
    /**
     * User u1 = new User("iboneurquiola", "iboneurquiola@gmail.com", "12345");
     *         User u2 = new User("laurallorente", "laurallorente@gmail.com", "6789");
     *         User u3 = new User("gabri98garaizabal", "gabri98garaizabal@gmail.com", "abcd");
     *         User u4 = new User("maartenhandels_", "maartenhandels_@gmail.com", "efgh");
     *         User u5 = new User("neresolaba1234", "neresolaba1234@gmail.com", "ijkl");
     *         User u6 = new User("anonimo33", "anonimo33@gmail.com", "mnop");
     *         User u7 = new User("landerpison57", "landerpison57@gmail.com", "qrst");
     *
     *          db_user.add(u1);
     *         db_user.add(u2);
     *         db_user.add(u3);
     *         db_user.add(u4);
     *         db_user.add(u5);
     *         db_user.add(u6);
     *         db_user.add(u7);
     */


    @Autowired
    public FakeUserDataAccessService(PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;

        applicationUsers  = new ArrayList<>();
        applicationUsers.add(new User(
                "tom@gmail.com",
                passwordEncoder.encode("password"),
                null,
                true,
                true,
                true,
                true
        ));
    }

    @Override
    public Optional<User> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    @Override
    public void addUser(User newUser) {
        // Antes de guardar codificar la contraseña
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        applicationUsers.add(newUser);

    }

    private List<User> getApplicationUsers() {
        return applicationUsers;
    }


}
