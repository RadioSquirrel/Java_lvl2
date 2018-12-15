import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.net.*;
import java.io.*;

public class FXMLDocumentController implements Initializable {
    
    private boolean authorized;
    private final static long AUTH_TIMEOUT = 120 * 1000;
    private boolean isAuth = false;
    
    public void setAthorized(boolean authorized)
    {
        this.authorized = authorized;
        if(authorized)
        {
          authPanel.setVisible(false);
          authPanel.setManaged(false);
          msgPanel.setVisible(true);
          msgPanel.setManaged(true);
        }
        else
        {
          authPanel.setVisible(true);
          authPanel.setManaged(true);
          msgPanel.setVisible(false);
          msgPanel.setManaged(false);
        }
    }
    
    private Socket socket;
    
    private DataInputStream  in;
    private DataOutputStream out;

    @FXML
    private Button sendButton, connectBtn;
    
    @FXML
    private TextArea TextArea;
    
    @FXML
    private TextField TextField, loginField;
   
    @FXML
    private PasswordField passwordField;
    
    @FXML
    HBox authPanel, msgPanel;
    
    @FXML
    AnchorPane MainAnchorPane;
    
    
    
    public void sendMsg() {
       
        try 
        {
            String str = TextField.getText();
            out.writeUTF(str);
            TextField.clear();
            TextField.requestFocus();
        } 
        catch (IOException ex) 
        {
            ex.getStackTrace();
        }
       
    }
    
    public void sendMsg(String msg) {
       
        try 
        {
            out.writeUTF(msg);
        } 
        catch (IOException ex) 
        {
            ex.getStackTrace();
        }
    }
    
    public void sendAuth() {
      
        connect();
        sendMsg("/auth " + loginField.getText() + " " + passwordField.getText());
        loginField.clear();
        passwordField.clear();
    }
    
    public void connect()
    {
       if(socket==null || socket.isClosed())
       {
        try
        {

          socket = new Socket("localhost", 12345);
          in  = new DataInputStream(socket.getInputStream());
          out = new DataOutputStream(socket.getOutputStream());


              new Thread(new Runnable(){

                  @Override
                  public void run()
                  {
                      try {
                          while(true)
                          {
                              System.out.println("Auth...");

                              String str = in.readUTF();
                              if(str.equals("/authok"))
                              {
                                  setAthorized(true);
                                  isAuth = true;
                                  break;
                              }
                          }
                          while(true)
                          {
                              String str = in.readUTF();
                              TextArea.appendText(str);
                              TextArea.appendText("\n");
                          }
                      } catch (IOException ex) {
                          ex.printStackTrace();
                      }
                      finally
                      {
                          try
                          {
                              in.close();
                              //out.close();
                              //socket.close();
                          }
                          catch(IOException ex)
                          {
                              ex.printStackTrace();
                          }
                          try
                          {
                              out.close();
                          }
                          catch(IOException ex)
                          {
                              ex.printStackTrace();
                          }
                          try
                          {
                              socket.close();
                          }
                          catch(IOException ex)
                          {
                              ex.printStackTrace();
                          }

                          setAthorized(false);

                      }
                  }
              }).start();

        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
       }//if
    }//public void connect()
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //URL              - for *.fxml
        //ResourceBundle   - for resources in a jar
        setAthorized(false);

        new Thread(()->{
            long start = System.currentTimeMillis();
            while (!isAuth) {
                if (System.currentTimeMillis() - start > AUTH_TIMEOUT + 2000) {
                    System.out.println("Client not auth. Timeout exceeded.");
                    if(socket!=null) sendMsg("/end");
                    Platform.exit();
                    System.exit(0);
                    break;
                }
            }
        }).start();
        
            Platform.runLater(() -> {
                
                ((Stage)MainAnchorPane.getScene().getWindow()).setOnCloseRequest(new EventHandler<WindowEvent>(){
                    
                    @Override
                    public void handle(WindowEvent t)
                    {
                        sendMsg("/end");
                        Platform.exit();
                        System.exit(0);
                    }
                });
        } //run
        );// Platform.runLater 
            
    }//public void initialize    
    
}//public class FXMLDocumentController