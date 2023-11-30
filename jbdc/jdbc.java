package jbdc;

import java.sql.*;
import java.util.Scanner;

public class jdbc {
 public static void main(String[] args) throws SQLException {
 Connection maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_sak","root","");
 Scanner scanner = new Scanner(System.in);

 System.out.println("Choisir l'option");
 int operation = scanner.nextInt();
 scanner.nextLine(); 

 switch(operation) {
  case 1:
  System.out.println("Nom:");
  String nom = scanner.nextLine();
  System.out.println("Prenom:");
  String prenom = scanner.nextLine();
  System.out.println("Email:");
  String email = scanner.nextLine();
  System.out.println("Mot de passe:");
  String mdp = scanner.nextLine();
  System.out.println("Votre age:");
  int age = scanner.nextInt();

  PreparedStatement maRequete = maConnection.prepareStatement("INSERT INTO user (nom, prenom, email, mdp, age) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
   maRequete.setString(1,nom);
   maRequete.setString(2,prenom);
   maRequete.setString(3,email);
   maRequete.setString(4,mdp); 
   maRequete.setInt(5,age);
  maRequete.execute();
   ResultSet userGenerer = maRequete.getGeneratedKeys();
   if (userGenerer.next()) {
    System.out.println("L'utilisateur a été inséré avec l'ID: " + userGenerer.getInt(1));
   }
   break;
   case 2:
   System.out.println("Ecrire l'id");
   int userId = scanner.nextInt();
   PreparedStatement fetchUserQuery = maConnection.prepareStatement("SELECT * FROM user WHERE id_user = ?");
   fetchUserQuery.setInt(1, userId);
   ResultSet resultSet = fetchUserQuery.executeQuery();
   while (resultSet.next()){
       System.out.println("User ID: " + resultSet.getInt("id_user"));
       System.out.println("Nom: " + resultSet.getString("nom"));
       System.out.println("Prenom: " + resultSet.getString("prenom"));
       System.out.println("Email: " + resultSet.getString("email"));
       System.out.println("Mot de passe: " + resultSet.getString("mdp"));
       System.out.println("Age: " + resultSet.getInt("age"));
   }
   
   break;
  case 3:

 
}
}
}
