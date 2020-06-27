package com.dim.sigepark.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.jackson.JsonComponent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@JsonComponent
public class JsonDeserializers {

	// serializamos en string
	@SuppressWarnings("serial")
	public static class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

		public LocalDateTimeSerializer() {
			super(LocalDateTime.class);
		}

		@Override
		public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
			gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		}
	}

	// deserializamos de string a localdatetime
	@SuppressWarnings("serial")
	public static class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

		public LocalDateTimeDeserializer() {
			super(LocalDateTime.class);
		}

		@Override
		public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {

			try {
				return LocalDateTime.parse(p.readValueAs(String.class));
			} catch (Exception e) {
				return null;
			}

		}
	}

}
