package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car = new Car("Mazda", 5);
        Car car2 = new Car("BMW", 1);
        Car car3 = new Car("Gelly", 200);
        User userEx = new User("User1", "Lastname1", "user1@mail.ru");
        userEx.setCar(car);
        userService.add(userEx);
        User userEx2 = new User("User2", "Lastname2", "user2@mail.ru");
        userEx2.setCar(car2);
        userService.add(userEx2);
        User userEx3 = new User("User3", "Lastname3", "user3@mail.ru");
        userEx3.setCar(car3);
        userService.add(new User("User4", "lastname4", "4@qwe.com", new Car("fast", 5)));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Drive " + user.getCar());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("fast", 5));
        System.out.println(userService.getUserByCar("BMW", 1));
        context.close();
    }
}
