import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args){
	    Connection conn = null;
	    Statement stmt = null;
	    String sql = "";
	    
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	conn = DriverManager.getConnection("jdbc:sqlite:inventario.db");
	    	conn.setAutoCommit(false);
	    	//System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            sql = "create table IF NOT EXISTS items (id text primary key not null, nombre text not null, desc text not null, cant int, precio real);";
            stmt.executeUpdate(sql);
            sql = "create table IF NOT EXISTS minimos (id int primary key not null, min int not null);";
            stmt.executeUpdate(sql);
            sql = "create table IF NOT EXISTS messages (id int primary key not null, message text not null);";
            stmt.executeUpdate(sql);
            sql = "create table IF NOT EXISTS contrasena (id int primary key not null, pass text not null);";
            stmt.executeUpdate(sql);
            sql = "create table IF NOT EXISTS proveedores (id text primary key not null, alias text not null, nombre text not null, direccion text not null, telefono text not null, rfc text not null);";
            stmt.executeUpdate(sql);
            conn.commit();
            try {
	            sql = "alter table items add column proveedor text";
	            stmt.executeUpdate(sql);
	            conn.commit();
            } catch (Exception e) {
            	System.err.println("added items: "+e.getClass().getName()+": "+e.getMessage());
            }
//            try {
//	            sql = "alter table proveedores add column telefono text";
//	            stmt.executeUpdate(sql);
//	            conn.commit();
//            } catch (Exception e) {
//            	System.err.println("adding telefono: "+e.getClass().getName()+": "+e.getMessage());
//            }
            try {
                sql = "insert into minimos(id,min) values(1,0);";
            	stmt.executeUpdate(sql);
            	conn.commit();
            } catch (Exception e) {
            	System.out.println("Los minimos ya han sido insertados");
            	System.err.println(e.getClass().getName()+": "+e.getMessage());
            }
            try {
                sql = "insert into messages(id,message) values(1,'El numero de articulos ha\nalcanzado el minimo configurado');";
            	stmt.executeUpdate(sql);
            	conn.commit();
            } catch (Exception e) {
            	System.out.println("Los mensajes ya han sido insertados");
            	System.err.println(e.getClass().getName()+": "+e.getMessage());
            }
            try {
                sql = "insert into contrasena(id,pass) values(1,'SCIpass');";
            	stmt.executeUpdate(sql);
            	conn.commit();
            } catch (Exception e) {
            	System.out.println("La contrasena ya ha sido insertada");
            	System.err.println(e.getClass().getName()+": "+e.getMessage());
            }
//	    	conn.commit();
	    	stmt.close();
	    	conn.close();
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	      
	    	System.exit(0);
	    } finally {
	    	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    //System.out.println("Table created successfully");
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}