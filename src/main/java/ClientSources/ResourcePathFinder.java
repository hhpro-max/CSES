package ClientSources;

public class ResourcePathFinder {
    public String getName(ImageResource type){
        switch (type){
            case CAPCHA1:
                return "capcha1.png";
            case CAPCHA2:
                return "capcha2.png";
            case CAPCHA3:
                return "capcha3.png";
            case CAPCHA4:
                return "capcha4.png";
            case CAPCHA5:
                return "capcha5.png";
            case CAPCHA6:
                return "capcha6.png";
            case NULL_PROFILE:
                return "nullProfile.jpg";
            default:
                return type.toString();
        }
    }
}
