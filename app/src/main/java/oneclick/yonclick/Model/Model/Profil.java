package oneclick.yonclick.Model.Model;

import java.io.Serializable;
import java.util.ArrayList;

import oneclick.yonclick.R;

public class Profil implements Serializable {

    String name;
    String id;
    int image;

    public Profil() {
    }

    public Profil(String name, String id, int image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public static ArrayList<Profil> fromFakeData()
    {
        ArrayList<Profil> results = new ArrayList<>();

        Profil Equipe = new Profil();
        Equipe.setImage(R.drawable.ic_edit);
        Equipe.setName("Modifier votre nom");

        Profil historic = new Profil();
        historic.setImage(R.drawable.ic_historic);
        historic.setName("Historique d'achats");

        Profil apropos = new Profil();
        apropos.setImage(R.drawable.ic_help);
        apropos.setName("Apropos");


        Profil share = new Profil();
        share.setImage(R.drawable.ic_share);
        share.setName("Partager avec un(e) ami(e)");


        results.add(Equipe);
        results.add(historic);
        results.add(share);
        results.add(apropos);


        return results;
    }
}
