package com.evaluation.dp;

import java.util.ArrayList;

public class DetailsPokemonApi {
        ArrayList<Object> abilities = new ArrayList<Object>();
        private float base_experience;
        Cries CriesObject;
        ArrayList<Object> forms = new ArrayList<Object>();



        public float getBase_experience() {
            return base_experience;
        }

        public Cries getCries() {
            return CriesObject;
        }

        public void setBase_experience( float base_experience ) {
            this.base_experience = base_experience;
        }

        public void setCries( Cries criesObject ) {
            this.CriesObject = criesObject;
        }
    public class Cries {
        private String latest;
        private String legacy;



        public String getLatest() {
            return latest;
        }

        public String getLegacy() {
            return legacy;
        }

        // Setter Methods

        public void setLatest( String latest ) {
            this.latest = latest;
        }

        public void setLegacy( String legacy ) {
            this.legacy = legacy;
        }
    }
}
