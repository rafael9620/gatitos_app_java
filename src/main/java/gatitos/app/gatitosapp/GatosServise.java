/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatitos.app.gatitosapp;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author FranciscoR
 */
public class GatosServise {

    public static void verGatos() throws IOException {

        OkHttpClient client = new OkHttpClient();//.newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String elJson = response.body().string();
        //Cortar los caracteres que no no sirven

        elJson = elJson.substring(1, elJson.length());
        elJson = elJson.substring(0, elJson.length() - 1);

        //Crear onjeto de la clase Json
        Gson gson = new Gson();
        Gato gato = gson.fromJson(elJson, Gato.class);

        // Redimencionar en caso de ser necesario
        Image image = null;
        try {
            URL url = new URL(gato.getUrl());
            image = ImageIO.read(url);

            ImageIcon fondoGato = new ImageIcon(image);

            if (fondoGato.getIconWidth() > 800) {
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);

            }

            String menu = "Opciones: \n"
                    + " 1. ver otra imagen \n"
                    + " 2. Favorito \n"
                    + " 3. Volver \n";

            String[] botones = {"ver otra imagen", "favorito", "volver"};
            String id_gato = String.valueOf(gato.getIdGato());
            String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

            int seleccion = -1;
            //validamos que opcion selecciona el usuario
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i])) {
                    seleccion = i;
                }
            }

            switch (seleccion) {
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gato);
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void favoritoGato(Gato gato) {
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n\t\"image_id\":\"" + gato.getIdGato() + "\"\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.getKEY())
                    .build();
            Response response = client.newCall(request).execute();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void verFavorito(String KEY) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", KEY)
                .build();

        Response response = client.newCall(request).execute();

        // guardamos el string con la respuesta
        String elJson = response.body().string();

        //creamos el objeto gson
        Gson gson = new Gson();

        GatosFav[] gatosArray = gson.fromJson(elJson, GatosFav[].class);

        if (gatosArray.length > 0) {
            int min = 1;
            int max = gatosArray.length;
            int aleatorio = (int) (Math.random() * ((max - min) - 1)) + min;
            int indice = aleatorio - 1;

            GatosFav gatofav = gatosArray[indice];
            //redimensionar en caso de necesitar
            Image image = null;
            try {
                URL url = new URL(gatofav.image.getUrl());
                image = ImageIO.read(url);

                ImageIcon fondoGato = new ImageIcon(image);

                if (fondoGato.getIconWidth() > 800) {
                    //redimensionamos
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }

                String menu = "Opciones: \n"
                        + " 1. ver otra imagen \n"
                        + " 2. Eliminar Favorito \n"
                        + " 3. Volver \n";

                String[] botones = {"ver otra imagen", "eliminar favorito", "volver"};
                String id_gato = gatofav.getId();
                String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE, fondoGato, botones, botones[0]);

                int seleccion = -1;
                //validamos que opcion selecciona el usuario
                for (int i = 0; i < botones.length; i++) {
                    if (opcion.equals(botones[i])) {
                        seleccion = i;
                    }
                }

                switch (seleccion) {
                    case 0:
                        verFavorito(KEY);
                        break;
                    case 1:
                        borrarFavorito(gatofav);
                        break;
                    default:
                        break;
                }

            } catch (IOException e) {
                System.out.println(e);
            }

        }

    }

    public static void borrarFavorito(GatosFav gatofav) {

    }
}
