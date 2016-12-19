In NmsClientUtil
------------------
CurrentPassword:
-------------------
curPasswd = p.getProperty("curpassword");
key1 = URLEncoder.encode(AuthUtil.encryptString(p.getProperty("curpassword"),challenge));
urlc.setRequestProperty("curPassword",key1);

NewPassword:
----------------
key        = URLEncoder.encode(AuthUtil.encryptString(p.getProperty("password"),challenge);
urlc.setRequestProperty("newPassword",key);

passwordString = AuthUtil.encryptString(p.getProperty("password"),"RtoPUX");


ChangePasswordServlet:
----------------------------
oldPassword = URLDecoder.decode(oldPassword);
newPassword = URLDecoder.decode(newPassword);

result = userConfigAPI.changePassword(userName, AuthUtil.decryptString(oldPassword,"WqrTnvA"),AuthUtil.decryptString(newPassword,"WqrTnvA"));

UserConfigAPIImpl:
--------------------
resultOfOperation = authorizationAPI.changePassword(userName,oldPassword,newPassword);

AuthorizationAdminImpl:
----------------------
RelationalAuthData.changePass(userName,old_pass,new_pass);

RelationalAuthData:
--------------------
Result of {select count(*) from UserPasswordTable where USERNAME=?  AND  (old)PASSWORD = "the given old password"}

String encrypt_pass = Coding.convertToNewBase(new_pass);
ps = relapi.fetchPreparedStatement(preparedStatementForUpdatePasswordID);
PreparedStatement preparedStatementForUpdatePassword = ps.getPreparedStatement();
preparedStatementForUpdatePassword.setString(1, encrypt_pass);
preparedStatementForUpdatePassword.setString(2, userName);
relapi.executeUpdate(preparedStatementForUpdatePassword);
