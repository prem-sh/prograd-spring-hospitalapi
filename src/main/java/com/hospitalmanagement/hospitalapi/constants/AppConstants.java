package com.hospitalmanagement.hospitalapi.constants;

public class AppConstants {
    public static enum AppointmentStatus {
        ACTIVE, CANCELLED, CLOSED;
        public int id(){
            return this.ordinal()+1;
        }
        public static boolean validate(String status){
            for(AppointmentStatus s : AppointmentStatus.values()){
                if(s.toString().equalsIgnoreCase(status)) return true;
            }
            return false;
        }
        public static AppointmentStatus get(String val){
            return AppointmentStatus.valueOf(val.toUpperCase());
        }
    }
}
