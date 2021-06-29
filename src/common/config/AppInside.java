package common.config;

public class AppInside {
    private final String ServletCodingType = "utf-8";
    private final String ServletEncodingType = "utf-8";
    private final String ArgsType = "application/json";

    public String getServletCodingType() {
        return ServletCodingType;
    }

    public String getServletEncodingType() {
        return ServletEncodingType;
    }

    public String getArgsType() {
        return ArgsType;
    }
}