package gatitos.app.gatitosapp;

/**
 *
 * @author FranciscoR
 */
public class GatosFav {

    private String id;
    private String image_id;
    private String apikey = "979887f9-7d8c-40a5-98a7-7a85d19eb2ed";
    Imagex image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Imagex getImage() {
        return image;
    }

    public void setImage(Imagex image) {
        this.image = image;
    }

}
