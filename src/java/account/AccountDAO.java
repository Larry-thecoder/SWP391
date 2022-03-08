/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author Mr.Khuong
 */
public class AccountDAO {
    public AccountDTO checkLogin(String accountID, String password) throws SQLException{
        AccountDTO ac = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            con = DBUtils.getConnection();
            if(con!=null){
                String sql = " SELECT userName, role "
                        + " FROM tblAccount WHERE accountID=? AND password=? ";
                pstm= con.prepareStatement(sql);
                pstm.setString(1, accountID);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                if(rs.next()){
                    String userName= rs.getString("userName");
                    String role = rs.getString("role");
                    ac = new AccountDTO(accountID, userName, "", role);
                }
            }
        }catch(Exception e){
            
        }finally{
           if(rs!=null) rs.close();
           if(pstm!=null) pstm.close();
           if(con!=null) con.close();
        }
        return ac;
    }
}
