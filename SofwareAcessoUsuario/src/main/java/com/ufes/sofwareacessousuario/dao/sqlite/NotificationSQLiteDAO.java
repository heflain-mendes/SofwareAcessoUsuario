/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.sofwareacessousuario.dao.sqlite;

import com.ufes.sofwareacessousuario.model.Notification;
import com.ufes.sofwareacessousuario.model.NotificationDTO;
import com.ufes.sofwareacessousuario.dao.service.UserRetorno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ufes.sofwareacessousuario.dao.interfaces.INotificationDAOProxy;

/**
 *
 * @author Heflain
 */
public class NotificationSQLiteDAO implements INotificationDAOProxy {
    private String caminho;

    public NotificationSQLiteDAO(String caminho) throws Exception {
        this.caminho = caminho;
        String sql = "CREATE TABLE IF NOT EXISTS notificacoes ("
                + "    id          INTEGER PRIMARY KEY AUTOINCREMENT"
                + "                        UNIQUE"
                + "                        NOT NULL,"
                + "    idremetente INTEGER NOT NULL"
                + "                        REFERENCES User (id),"
                + "    idreceptor  INTEGER NOT NULL"
                + "                        REFERENCES User (id),"
                + "    assunto     TEXT    NOT NULL,"
                + "    mensagem    TEXT    NOT NULL,"
                + "    estado      INTEGER NOT NULL DEFAULT 0"
                + ");";
        
        try (
                Connection conn = DriverManager.getConnection(caminho);
                Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Não foi possivel criar a tabela notificacoes");
        }
    }

    @Override
    public void enviarNoticacao(Notification notification) throws Exception {
        String sql = "INSERT INTO notificacoes (idremetente, idreceptor, assunto, mensagem) VALUES(?, ?, ?, ?)";

        try (
                Connection conn = DriverManager.getConnection(caminho);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, notification.getIdRemetente());
            ps.setLong(2, notification.getIdReceptor());
            ps.setString(3, notification.getAssunto());
            ps.setString(4, notification.getMensagem());

            ps.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception(
                    "Não foi possivel salvar informações da mensagem com assunto : "
                    + notification.getAssunto()
            );
        }
    }
    
//    private NotificationDTO getUltimaNotificacaoAdd() throws Exception{
//        String sql = " SELECT * FROM notificacoes where id = (SELECT MAX(id) FROM notificacoes);";
//
//        try (
//                Connection conn = DriverManager.getConnection(caminho);
//                PreparedStatement ps = conn.prepareStatement(sql);
//                ResultSet rs = ps.executeQuery()) {
//            if (rs.next()) {
//                return new NotificationDTO(
//                    rs.getLong("id"), 
//                    rs.getLong("idremetente"),
//                    rs.getString("assunto"), 
//                    rs.getString("mensagem"),
//                    rs.getInt("estado")
//                );
//            }
//
//        } catch (Exception e) {
//            throw new Exception("Não foi possivel obter o usuário na tabela usuário");
//        }
//
//        return null;
//    }

    @Override
    public int qtdNotifications(UserRetorno user) throws Exception {
        String sql = "SELECT COUNT(id) FROM notificacoes;";

        try (
                Connection conn = DriverManager.getConnection(caminho); 
                PreparedStatement ps = conn.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()) {
            rs.next();
            return rs.getInt(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Não foi possivel obter quantidade de notificações  recebidas pelo usuário: "
                    + user.getName());
        }
    }

    @Override
    public List<NotificationDTO> getNotifications(UserRetorno user) throws Exception {
        String sql = "SELECT * FROM notificacoes where idreceptor = ?";

        List<NotificationDTO> notificacoes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(caminho)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, user.getId());
            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                    notificacoes.add(new NotificationDTO(
                            rs.getLong("id"),
                            rs.getLong("idremetente"),
                            rs.getString("assunto"),
                            rs.getString("mensagem"),
                            rs.getInt("estado")));
                }
                return notificacoes;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possivel obter todas as notificações "
                    + "do usuário: "
                    + user.getName());
        }
    }

    @Override
    public void marcaComoLida(long id) throws Exception {
        String sql = "UPDATE notificacoes SET estado=? WHERE id=?;";

        try (
                Connection conn = DriverManager.getConnection(caminho);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Notification.LIDO);
            ps.setLong(2, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Não foi possivel marca mensagem como lida");
        }
    }
}
