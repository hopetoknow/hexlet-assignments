package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.ArrayList;
import java.util.List;

// BEGIN
public class TcpConnection implements Connection {

    private final List<String> datalist = new ArrayList<>();
    private Connection connection;
    private String address;
    private int port;

    public TcpConnection(String address, int port) {
        this.address = address;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setData(String data) {
        datalist.add(data);
    }

    @Override
    public String getCurrentState() {
        return connection.getCurrentState();
    }

    @Override
    public void connect() {
        connection.connect();
    }

    @Override
    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public void write(String data) {
        connection.write(data);
    }
}
// END
