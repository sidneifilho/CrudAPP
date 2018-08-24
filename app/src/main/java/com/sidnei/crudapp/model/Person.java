package com.sidnei.crudapp.model;

import java.io.Serializable;
import java.util.InputMismatchException;

public class Person implements Serializable{

    public enum SEX{ FEMALE, MALE, OTHER}

    private int id;
    private String name;
    private String cpf;
    private String cep;
    private String uf;
    private String address;
    private SEX sex;

    public Person(){
        clearValues();
    }

    public Person(String name, String cpf, String cep, String uf, String address, SEX sex){
        this.name = name;
        this.cpf = cpf;
        this.cep = cep;
        this.uf  = uf;
        this.address = address;
        this.sex = sex;
    }

    public Person(int id, String name, String cpf, String cep, String uf, String address, SEX sex){
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.cep = cep;
        this.uf  = uf;
        this.address = address;
        this.sex = sex;
    }

    /**
     * Function used to valid a cpf string, this code was acquire from the link:
     * <a href="https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097">
     * */
    public boolean isValidCpf(){
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") || (cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            }else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            }else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados, se sim CPF ok, senão CPF inválido.
            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException erro) {
            return(false);
        }

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf(){
        return uf;
    }

    public void setUf(String uf){
        this.uf = uf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    public void setSex(String sex){
        switch (sex){
            case "f":
                this.sex = SEX.FEMALE;
                break;
            case "m":
                this.sex = SEX.MALE;
                break;
            case "o":
                this.sex = SEX.OTHER;
                break;
            default:
                this.sex = SEX.OTHER;
                break;
        }
    }

    public void clearValues(){
        id = 0;
        name = "";
        cpf = "";
        cep = "";
        uf = "";
        address = "";
        sex = SEX.OTHER;
    }

}
