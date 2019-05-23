package com.lambdaschool.health;

import com.lambdaschool.health.model.*;
import com.lambdaschool.health.repository.RoleRepository;
import com.lambdaschool.health.repository.UserInfoRepository;
import com.lambdaschool.health.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.hibernate.mapping.Array;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    UserInfoRepository inforepo;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, UserInfoRepository inforepo)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.inforepo = inforepo;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = rolerepos.save(r1);
        r2 = rolerepos.save(r2);
        r3 = rolerepos.save(r3);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> data = new ArrayList<>();
        data.add(new UserRoles(new User(), r2));
        data.add(new UserRoles(new User(), r3));


        User u1 = new User("test", "1234", users);

        User u2 = new User("admin", "1234", admins);

        User u3 = new User("sam", "1234", data);

        UserInfo i3 = new UserInfo("sam","Sam", "Rodriguez", 140, "Male", 24,69,0, "To eat an entire pizza solo", 1);

        inforepo.save(i3);
        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);


    }
}
