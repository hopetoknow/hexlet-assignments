package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already established");
    }

    @Override
    public void disconnect() {
        tcpConnection.setConnection(new Disconnected(tcpConnection));
    }

    @Override
    public void write(String data) {
        tcpConnection.setData(data);
    }
}
// END
