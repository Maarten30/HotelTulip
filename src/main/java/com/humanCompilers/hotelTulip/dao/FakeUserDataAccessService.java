package com.humanCompilers.hotelTulip.dao;

import com.humanCompilers.hotelTulip.model.User;
import org.springframework.stereotype.Repository;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

@Repository("fakeUserDao")
public class FakeUserDataAccessService implements UserDao {

    private static List<User> db_user = new ArrayList<>();

    public FakeUserDataAccessService()
    {
        // Usuarios que metemos en el array para simular la BD.

        User u1 = new User("iboneurquiola", "iboneurquiola@gmail.com", "12345");
        User u2 = new User("laurallorente", "laurallorente@gmail.com", "6789");
        User u3 = new User("gabri98garaizabal", "gabri98garaizabal@gmail.com", "abcd");
        User u4 = new User("maartenhandels_", "maartenhandels_@gmail.com", "efgh");
        User u5 = new User("neresolaba1234", "neresolaba1234@gmail.com", "ijkl");
        User u6 = new User("anonimo33", "anonimo33@gmail.com", "mnop");
        User u7 = new User("landerpison57", "landerpison57@gmail.com", "qrst");

        db_user.add(u1);
        db_user.add(u2);
        db_user.add(u3);
        db_user.add(u4);
        db_user.add(u5);
        db_user.add(u6);
        db_user.add(u7);
    }
    @Override
    public int insertUser(User user) {

       /* for (int i = 0; i < db_user.size(); i++)
        {
            if(user.getUsername()==db_user.get(i).getUsername())
            {
                System.out.println("Este usuario ya existe. Por favor, escoja otro nombre.");
            }
            else
            {
                db_user.add(user);
                System.out.println("Su registro se ha completado.");
            }
        }*/

        db_user.add(user);
        return 1;
    }

    @Override
    public List<User> selectAllUser() {
        return db_user;
    }
}
