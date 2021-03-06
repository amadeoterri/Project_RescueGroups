package com.example.project_rescuegroups.response;

import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.model.Organization;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

public class JSONResponseImplSingle {

    public Animal handleAnimalResponse(String data) throws IOException {
        try {
            JSONObject animal = (JSONObject) new JSONTokener(data).nextValue();
            //id uithalen
            String id = animal.getString("animalID");
            //naam uithalen
            String name = animal.getString("name");
            //species uithalen
            String species = animal.getString("species");
            //breed uithalen
            String breed = animal.getString("breed");
            //animalsex uithalen
            String sex = animal.getString("sex");
            //animalbirthdate uithalen
            String birthdate = animal.getString("birthdate");
            //description uithalen
            String description = animal.getString("descriptionPlain");
            //image uithalen via url
            JSONArray jsonArray = animal.getJSONArray("pictures");
            //organization uithalen
            String animalOrgID = animal.getString("orgID");
            //als het dier geen foto's heeft, niet toevoegen
            if (name.length() > 0 && species.length() > 0 && breed.length() > 0 && sex.length() > 0
                    && birthdate.length() > 0 && jsonArray.length() > 0 && description.length() > 0
                    && animalOrgID.length() > 0) {
                JSONObject object = jsonArray.getJSONObject(0);
                String url = object.getString("originalUrl");
                return new Animal(id, name, species, breed, sex, birthdate, url, description, animalOrgID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Organization handleOrganizationResponse(String data) throws IOException {
        try {
            JSONObject organization = (JSONObject) new JSONTokener(data).nextValue();
            //id uithalen
            String id = organization.getString("orgID");
            //naam uithalen
            String name = organization.getString("name");
            //address uithalen
            String address = organization.getString("address");
            //city uithalen
            String city = organization.getString("city");
            //state uithalen
            String state = organization.getString("state");
            //zip uithalen
            String zip = organization.getString("zip");
            //country uithalen
            String country = organization.getString("country");
            //phonenumber uithalen
            String phone = organization.getString("phone");
            //email uithalen
            String email = organization.getString("email");
            //als het dier geen foto's heeft, niet toevoegen
            if (name.length() > 0 && address.length() > 0 && city.length() > 0 && state.length() > 0 && zip.length() > 0 && country.length() > 0 && phone.length() > 0 && email.length() > 0) {
                return new Organization(id, name, address, city, state, zip, country, phone, email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
