package com.fanou.facialrecognition.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fanou.facialrecognition.model.UploadedImage;

@Service
public class ImagePredictionService  {
   @Autowired
   UserService userServices;
   public UploadedImage labelPredict(String filePath, Integer id) throws IOException{  
      // Commande à exécuter
      String[] cmd = {"python3", "prediction.py", filePath};

      // Création du ProcessBuilder
      ProcessBuilder pb = new ProcessBuilder(cmd);

      // Redirection de la sortie standard et d'erreur
      pb.redirectErrorStream(true);

      // Lancement de la commande
      Process process = pb.start();

      // Récupération de la sortie de la commande
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String label="",line;
      
      while ((line = reader.readLine()) != null)  {
         System.out.println(line);
         label = line;
      }
      return new UploadedImage(filePath,label,userServices.getUserByID(id));
   }
}

