package com.gry.cable.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

public class BaseDao {

	
	private static BaseDao baseDao = null; //静态成员变量，支持单态模式
	private PropertyResourceBundle bundle; //配置资源文件
	private static String jdbcDrive = null; //JDBC驱动类型
	private String DBhost = ""; //数据库主机地址
	private String DBname = ""; //数据库名
	private String DBprot = ""; //数据库端口
	private String DBuser = ""; //数据库用户名
	private String DBpasswd = ""; //数据库密码
	private String strcon = null; //连接字符串

	private Connection conn = null; //连接对象    
	private PreparedStatement pstm = null;
	
	private BaseDao(){
		try
		{
			// 读取配置文件
			bundle = new PropertyResourceBundle(BaseDao.class
					.getResourceAsStream("Config.properties"));
			this.DBhost = getString("DBhost"); //读取主机名
			this.DBname = getString("DBname"); //读取用户名
			this.DBprot = getString("DBport"); //读取端口
			this.DBuser = getString("DBuser"); //读取用户
			this.DBpasswd = getString("DBpassword"); //读取密码
			String databese_type = getString("database-type"); //读取数据库类型
			if (databese_type != null) //如果类型不为空
			{
				if (databese_type.toLowerCase().equals("mysql"))
				{ // 设置mysql数据库的驱动程序和连接字符
					jdbcDrive = "com.mysql.jdbc.Driver";
					strcon = "jdbc:mysql://" + DBhost + ":" + DBprot + "/"
							+ DBname;
				}
				else
					if (databese_type.toLowerCase().equals("oracle"))
					{ // 设置oracle数据库的驱动程序和连接字符
						jdbcDrive = "oracle.jdbc.driver.OracleDrive";
						strcon = "jdbc:oracle:thin:@" + DBhost + ":" + DBprot
								+ ":" + DBname;
					}
					else
						if (databese_type.toLowerCase().equals("sqlserver2000"))
						{ // 设置sqlserver2000数据库的驱动程序和连接字符
							jdbcDrive = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
							strcon = "jdbc:micorsoft:sqlserver://" + DBhost
									+ ":" + DBprot + ";DatabaseName=" + DBname;
						}
						else
							if (databese_type.toLowerCase().equals(
									"sqlserver2005"))
							{ // 设置sqlserver2005数据库的驱动程序和连接字符
								jdbcDrive = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
								strcon = "jdbc:sqlserver://" + DBhost + ":"
										+ DBprot + ";DatabaseName=" + DBname;
							}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int execInsert(String strSQL, Object[] params){
		connectDB();
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(strSQL);
			for(int i=0;i<params.length;i++){
				DetermineParamType.determine(ps, i+1, params[i]);
			}
			ps.executeUpdate();
			commitChange();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnHelper.close(ps);
			ConnHelper.close(conn);
		}
		
		return -1;
	}
	
	
	/**
	 *  读取配置文件中的值
	 * @param key 配置文件的key
	 * @return  key对应的值
	 */
	private String getString(String key)
	{
		return this.bundle.getString(key);
	
	}
	
	/**
	 * 单态模式获取实例
	 * @return SqlManager对象
	 */
	public static BaseDao createInstance()
	{
		if (baseDao == null)
		{
			baseDao = new BaseDao();
			baseDao.initDB();
		}
		return baseDao;
	}
	
	/**
	 *  初始化连接参数，由指定的DBType生成
	 */
	public void initDB()
	{
		try
		{
			Class.forName(jdbcDrive);
		}
		catch (Exception e)
		{
			System.err.println("initDB Error!" + e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 *  连接数据库
	 */
	public void connectDB()
	{
		try
		{
			conn = DriverManager.getConnection(strcon, DBuser, DBpasswd); // 获取连接
			conn.setAutoCommit(false); //设置自动提交为false
		}
		catch (Exception e)
		{
			System.err.println("connectDB Error!" + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 设置PrepareStatement对象中Sql语句中的参数
	 * @param sql sql语句
	 * @param params 参数列表
	 */
	@SuppressWarnings("unused")
	private void setPrepareStatementParams(String sql, Object[] params)
	{
		try
		{
			pstm = conn.prepareStatement(sql); //获取对象
			if (params != null)
			{
				for (int i = 0; i < params.length; i++) // 遍历参数列表填充参数
				{
					pstm.setObject(i + 1, params[i]);
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("setPrepareStatementParams Error!"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交信息到数据库
	 * @throws SQLException
	 */
	private void commitChange() throws SQLException
	{
		try
		{
			conn.commit();
			System.out.println("数据提交成功！");
		}
		catch (Exception e)
		{
			conn.rollback();
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新数据库操作
	 * @param sql sql语句
	 * @param params 参数列表
	 * @param type sql语句的类型
	 * @return 执行操作的结果
	 */
	public int execUpdate(String strSQL, Object[] params){
		connectDB();
		try {
			pstm = conn.prepareStatement(strSQL);
			for(int i=0;i<params.length;i++){
				DetermineParamType.determine(pstm, i+1, params[i]);
			}
			pstm.executeUpdate();
			commitChange();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			ConnHelper.close(pstm);
			ConnHelper.close(conn);
		}
		
		return -1;
	}
	
	public List<Map<String,Object>> execQuery(String strSQL, Object[] params){
		connectDB();
		ResultSet rs = null;
		List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>(1);
		
		try {
			pstm = conn.prepareStatement(strSQL);
			for(int i=0;i<params.length;i++){
				DetermineParamType.determine(pstm, i+1, params[i]);
			}
			
			rs = pstm.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while(rs.next()){
				int cc = metaData.getColumnCount();
				//Map<String,Object> mapRS =new CaseInsensitiveMap();// new HashMap<String,Object>();
				Map<String,Object> mapRS = new HashMap<String,Object>();
				for(int i=1;i<=cc;i++){
					mapRS.put(metaData.getColumnName(i), rs.getObject(i));
				}
				rsList.add(mapRS);
			}
			
			return rsList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnHelper.close(rs);
			ConnHelper.close(pstm);
			ConnHelper.close(conn);
		}
		
		return rsList;
	}
	
}
