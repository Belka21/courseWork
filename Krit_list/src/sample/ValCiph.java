package sample;

//Перечисление шифра ценности груза

public enum ValCiph {
    NON("Не задан"),
    NORMAL("Обычный"),
    VALUABLE("Ценный"),
    SUPER_VALUABLE("Супер-ценный"),
    NORMAL_FRAGILE("Обычный хрупкий"),
    VALUABLE_FRAGILE("Ценный хрупкий"),
    SUPER_VALUABLE_FRAGILE("Супер-ценный хрупкий");

    private  String ciph;

    private ValCiph(String cipher)
    {
        this.ciph=cipher;
    }
}
