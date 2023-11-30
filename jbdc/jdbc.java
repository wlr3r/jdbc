package jbdc;

import java.sql.*;
import java.util.Scanner;

public class jdbc {
 public static void main(String[] args) throws SQLException {
 Connection maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sle_exJDBC1","root","");
 Scanner scanner = new Scanner(System.in);

 System.out.println("Choisir l'option");
 int operation = scanner.nextInt();
 scanner.nextLine(); 

 switch(operation) {
  case 1:
   System.out.println("Nom:");
   String nom = scanner.nextLine();
   System.out.println("Prénom:");
   String prenom = scanner.nextLine();
   System.out.println("Email:");
   String email = scanner.nextLine();
   System.out.println("Mot de passe:");
   String mdp = scanner.nextLine();

   PreparedStatement maRequete = maConnection.prepareStatement("INSERT INTO user (nom,prenom,email,mdp) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
   maRequete.setString(1,nom);
   maRequete.setString(2,prenom);
   maRequete.setString(3,email);
   maRequete.setString(4,mdp);
   maRequete.execute();
   ResultSet userGenerer = maRequete.getGeneratedKeys();
   if (userGenerer.next()) {
    System.out.println("L'utilisateur a été inséré avec l'ID: " + userGenerer.getInt(1));
   }
   break;
 case 2:
  System.out.println("Voici tout les users");
   int user = scanner.nextInt();
   PreparedStatement fetchUserQuery = maConnection.prepareStatement("SELECT * FROM user = ?"); }
}
}
