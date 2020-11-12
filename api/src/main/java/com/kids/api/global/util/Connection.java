package com.kids.api.global.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import lombok.Getter;

public class Connection {
    @Getter
    private HttpURLConnection connection;

    private Connection(HttpURLConnection connection) {
        this.connection = connection;
    }
    
    public static ConnectionBuilder builder(String method, String rawUrl) throws IOException {
        URL url = new URL(rawUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method.toUpperCase());
        
        return new ConnectionBuilder(connection);
    }

    public static class ConnectionBuilder {
        private HttpURLConnection __connection;
        
        private ConnectionBuilder(HttpURLConnection connection) {
            this.__connection = connection;
        }
        
        public ConnectionBuilder header(String key, String value) {
            this.__connection.setRequestProperty(key, value);
            
            return this;
        }

        public ConnectionBuilder body(JSONObject body) throws IOException {
            this.__connection.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.__connection.getOutputStream()));

            bw.write(body.toString());
            bw.flush();
            bw.close();
            
            return this;
        }
        
        public Connection build() {
            Connection connection = new Connection(this.__connection);
            return connection;
        }
    }
}
