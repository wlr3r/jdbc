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
   scanner.close();


  PreparedStatement maRequete = maConnection.prepareStatement("INSERT INTO user (nom, prenom, email, mdp, age) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
   maRequete.setString(1,nom);
   maRequete.setString(2,prenom);
   maRequete.setString(3,email);
   maRequete.setString(4,mdp); 
   maRequete.setInt(5,age);
  maRequete.execute();
   ResultSet Usercreer = maRequete.getGeneratedKeys();
   if (Usercreer.next()) {
    System.out.println("L'utilisateur a été inséré avec l'ID: " + Usercreer.getInt(1));
   }
   break;
   case 2:
   System.out.println("Ecrire l'id"); //avoir les donnés d'un user
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
        scanner.close();

   }

   break;
  case 3: //même baille que la case2
    PreparedStatement fetchAllUsersQuery = maConnection.prepareStatement("SELECT * FROM user");
    ResultSet resultatSet = fetchAllUsersQuery.executeQuery();
    while (resultatSet.next()){
      System.out.println("User ID: " + resultatSet.getInt("id_user"));
      System.out.println("Nom: " + resultatSet.getString("nom"));
      System.out.println("Prenom: " + resultatSet.getString("prenom"));
      System.out.println("Email: " + resultatSet.getString("email"));
      System.out.println("Mot de passe: " + resultatSet.getString("mdp"));
      System.out.println("Age: " + resultatSet.getInt("age"));
       scanner.close();

    }
    break;
    case 4:
    System.out.println("Quelle utilisateur voulez vous modifier ? Entrer son id ");
    int id = scanner.nextInt();
    PreparedStatement fetchUserQueryCase4 = maConnection.prepareStatement("SELECT * FROM user WHERE id_user = ?");
    fetchUserQueryCase4.setInt(1, id);
    ResultSet resultSetCase4 = fetchUserQueryCase4.executeQuery();
    if (resultSetCase4.next()){
        System.out.println("User ID: " + resultSetCase4.getInt("id_user"));
    }

    System.out.println("Que voulez vous modifier ? \n nom, prenom, email, mdp, age ");
    String fieldToModify = scanner.next();
    System.out.println("Entrez la nouvelle valeur: ");
    String newValue = scanner.next();

    PreparedStatement updateUserQuery = maConnection.prepareStatement("UPDATE user SET " + fieldToModify + " = ? WHERE id_user = ?");
    updateUserQuery.setString(1, newValue);
    updateUserQuery.setInt(2, id);
    updateUserQuery.executeUpdate();

    System.out.println("L'utilisateur a été modifié avec succès.");

    // voir les donner du nouveau user 
    PreparedStatement fetchUpdatedUserQuery = maConnection.prepareStatement("SELECT * FROM user WHERE id_user = ?");
    fetchUpdatedUserQuery.setInt(1, id);
    ResultSet updatedUserResultSet = fetchUpdatedUserQuery.executeQuery();
    while (updatedUserResultSet.next()){
        System.out.println("User ID: " + updatedUserResultSet.getInt("id_user"));
        System.out.println("Nom: " + updatedUserResultSet.getString("nom"));
        System.out.println("Prenom: " + updatedUserResultSet.getString("prenom"));
        System.out.println("Email: " + updatedUserResultSet.getString("email"));
        System.out.println("Mot de passe: " + updatedUserResultSet.getString("mdp"));
        System.out.println("Age: " + updatedUserResultSet.getInt("age"));
    }
    break;
 


 

}
}
}
