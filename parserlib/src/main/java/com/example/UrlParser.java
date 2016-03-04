package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class UrlParser {

    private String url;
    private Class<?> className;
    Constructor<?> cons;

    public UrlParser(String url, Class<?> className){
        this.url = url;
        this.className = className;
        setConstructor(this.className);
    }

    private String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    private List<?> getJason(){

        List<Object> values=new ArrayList<>();

        try {
            String json = getText(url);
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(json);
            JSONArray array = (JSONArray)obj;

            for (Object arr:array){

                List<Field> fields = getPrivateFields(className);
                Object[] val=new Object[fields.size()];
                for(int i=0;i<fields.size();i++){

                    JSONObject obj2= (JSONObject) arr;
                    val[i]=obj2.get(fields.get(i).getName());

                }
                values.add(cons.newInstance(val));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    public List<?> getValues(){
        return getJason();

    }


    public static List<Field> getPrivateFields(Class<?> theClass){



        List<Field> privateFields = new ArrayList<>();
        Field[] fields = theClass.getDeclaredFields();
        for(Field field:fields){
            if(Modifier.isPrivate(field.getModifiers())){
                privateFields.add(field);
            }
        }
        return privateFields;

    }

    public void setConstructor(Class<?> className){

        List<Field> fields = getPrivateFields(className);
        Class[] type = new Class[fields.size()];
        for(int i=0;i<fields.size();i++){
            type[i]=fields.get(i).getType();
        }

        try {
            cons = className.getConstructor(type);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }




    }





}
