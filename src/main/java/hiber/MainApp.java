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

      /*Добавляем юзеров*/

      userService.add(
              new User("User1", "Lastname1", "user1@mail.ru", new Car("Porshe", 2)));
      userService.add(
              new User("User2", "Lastname2", "user2@mail.ru", new Car("Lada", 11)));
      userService.add(
              new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 7)));
      userService.add(
              new User("User4", "Lastname4", "user4@mail.ru", new Car("Mers", 3)));
      userService.add(
              new User("User5", "Lastname5", "user5@mail.ru", new Car("Porshe", 2)));

      /*Получаем всех юзеров*/
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Series = "+user.getCar().getSeries());
         System.out.println();
      }

      System.out.println("-------------------------------------");
      System.out.println();

      /*Получаем юзеров по модели и серии car*/
      List<Car> carList = userService.getUsersByCar("Porshe", 2);
      for (Car car : carList) {
         User user = car.getUser();
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Series = "+user.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
