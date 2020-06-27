package com.project.seatingplan.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.project.seatingplan.config.JacksonMixinModule;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JsonObjectMapper extends ObjectMapper {
  private static final long serialVersionUID = 1L;

  public JsonObjectMapper() {
    JsonSerializer<Double> doubleJsonSerializer = new JsonSerializer<Double>() {
      @Override
      public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(aDouble);
      }
    };

    Map<Class<?>, JsonSerializer<?>> mapping = new HashMap<>();
    mapping.put(double.class, doubleJsonSerializer);

    this.registerModule(new JodaModule());
    this.registerModule(new JacksonMixinModule());
    Hibernate5Module module = new Hibernate5Module();
    module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
    this.enable(SerializationFeature.INDENT_OUTPUT);
  }
}
