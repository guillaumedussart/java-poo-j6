package fr.diginamic.recensement.services;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

import java.util.List;

public class DepartementService {
    public boolean listDept(Recensement rec,String dept) {
        List<Ville> villes = rec.getVilles();
        for(Ville r:villes){
            if(r.getCodeDepartement().equalsIgnoreCase(dept)){
                return true;
            }
        }
        return false;
    }
}
