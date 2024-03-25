package com.example.easerver.Handlers.ApplicantsHandlers;

import com.example.easerver.Entities.TypeEmEntity;
import com.example.easerver.Handlers.BaseHandlers.GetHandler;
import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;


//public class EmergencyTypesHandler extends GetHandler {
//
//    @Override
//    public String handleGet() {
//        try {
//            List<TypeEmEntity> list = dbManager.getAllTypesEntities();
//            Gson gson = new Gson();
//
//            List<TypesRep> types = list.stream()
//                    .map(i -> new TypesRep(i.getName(), i.getRecommendations())).collect(Collectors.toList());
//            return gson.toJson(types);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}