package conex;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class consultas {

	public String autenticacion(String Nombre, String Contraseña) {
		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			String consulta = "SELECT tu.tipo FROM Usuarios u "
					+ "JOIN tipos_usuario tu ON u.id_tipo_usuario = tu.id_tipo_usuario "
					+ "WHERE u.Nombre = ? AND u.Contraseña = ?";
			PreparedStatement pst = conexion.prepareStatement(consulta);
			pst.setString(1, Nombre);
			pst.setString(2, Contraseña);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getString("tipo");
			} else {
				return "Usuario no encontrado";
			}
		} catch (SQLException e) {
			System.out.println("Error al autenticar: " + e);
			return "Error al autenticar";
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}

	public String obtenerTipoUsuario(String usuario) {
		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			String consulta = "SELECT tu.tipo FROM Usuarios u "
					+ "JOIN tipos_usuario tu ON u.tipo_usuario = tu.id_tipo_usuario " + "WHERE u.username = ?";
			PreparedStatement pst = conexion.prepareStatement(consulta);
			pst.setString(1, usuario);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getString("tipo");
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.out.println("Error al obtener tipo de usuario: " + e);
			return null;
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}

	public boolean registrarUsuario(String Nombre, String Apellido, String Contraseña, String DNI, String Telefono,
			String Cargo, String id_tipo_usuario, Connection conexion) {
		try {
			String query = "INSERT INTO Usuarios (IdUsuarios, Nombre, Apellido, Contraseña, DNI, Telefono, Cargo, id_tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, null);
			pst.setString(2, Nombre);
			pst.setString(3, Apellido);
			pst.setString(4, Contraseña);
			pst.setString(5, DNI);
			pst.setString(6, Telefono);
			pst.setString(7, Cargo);
			pst.setString(8, id_tipo_usuario);

			int resultado = pst.executeUpdate();

			return resultado > 0;
		} catch (SQLException e) {
			System.out.println("Error al registrar usuario: " + e);
			return false;
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}

	public boolean registrarCliente(String Nombres, String Apellidos, String DNI, String Genero, String Direccion,
			String Telefono, String Correo, Connection conexion) {
		try {
			String query = "INSERT INTO Cliente (id_cliente, Nombres, Apellidos, DNI, Genero, Direccion, Telefono, Correo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, null);
			pst.setString(2, Nombres);
			pst.setString(3, Apellidos);
			pst.setString(4, DNI);
			pst.setString(5, Genero);
			pst.setString(6, Direccion);
			pst.setString(7, Telefono);
			pst.setString(8, Correo);

			int resultado = pst.executeUpdate();

			return resultado > 0;
		} catch (SQLException e) {
			System.out.println("Error al registrar Cliente: " + e);
			return false;
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}

	public boolean agregarProveedor(String Nombre, String Apellido, String RUC, String RazonSocial, String Direccion,
			String Telefono, String Correo, String Categoria) {

		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			// Use CURRENT_TIMESTAMP to set the current date and time
			String query = "INSERT INTO Proveedor (IdProveedor, Nombre, Apellido, RUC, RazonSocial, Direccion, Telefono, Correo, Categoria, FechaCreacion) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, null);
			pst.setString(2, Nombre);
			pst.setString(3, Apellido);
			pst.setString(4, RUC);
			pst.setString(5, RazonSocial);
			pst.setString(6, Direccion);
			pst.setString(7, Telefono);
			pst.setString(8, Correo);
			pst.setString(9, Categoria);

			int resultado = pst.executeUpdate();

			return resultado > 0;
		} catch (SQLException e) {
			System.out.println("Error al agregar proveedor: " + e);
			return false;
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}

	public boolean agregarProducto(String nombre, float precio, int cantidad, String marca, String descripcion) {

		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			String query = "INSERT INTO Productos (IdProductos, Nombre, Precio, Cantidad, Marca, Descripcion, FechaVencimiento, FechaIngreso) "
					+ "VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE)";

			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, null);
			pst.setString(2, nombre);
			pst.setFloat(3, precio);
			pst.setInt(4, cantidad);
			pst.setString(5, marca);
			pst.setString(6, descripcion);

			int resultado = pst.executeUpdate();

			return resultado > 0;
		} catch (SQLException e) {
			System.out.println("Error al agregar producto: " + e);
			return false;
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}

	public List<String> obtenerTiposMascota() {
		List<String> tiposMascota = new ArrayList<>();
		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			String consulta = "SELECT Tipo FROM TipoMascota";
			PreparedStatement pst = conexion.prepareStatement(consulta);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				tiposMascota.add(rs.getString("Tipo"));
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener tipos de mascota: " + e);
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}

		return tiposMascota;
	}

	public boolean registrarNuevaMascota(String nombre, String raza, String disponibilidad, String pedigree,
			String tipoMascota, String genero) {
		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			String query = "INSERT INTO AdopMascota (Nombre, Raza, Disponibilidad, Pedigree, FechaIngreso, FechaAdopcion, id_TipoMascota, Genero) "
					+ "VALUES (?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE, (SELECT id_TipoMascota FROM TipoMascota WHERE Tipo = ?),?)";
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, nombre);
			pst.setString(2, raza);
			pst.setString(3, disponibilidad);
			pst.setString(4, pedigree);
			pst.setString(5, tipoMascota);
			pst.setString(6, genero);

			int resultado = pst.executeUpdate();

			return resultado > 0;
		} catch (SQLException e) {
			System.out.println("Error al registrar nueva mascota: " + e);
			return false;
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}

	public boolean agregartipomascota(String Tipo) {

		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {

			String query = "INSERT INTO TipoMascota (id_TipoMascota, Tipo) " + "VALUES (?, ?)";

			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, null);
			pst.setString(2, Tipo);

			int resultado = pst.executeUpdate();

			return resultado > 0;
		} catch (SQLException e) {
			System.out.println("Error al agregar Tipo de Mascota: " + e);
			return false;
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}
	
	
	public boolean registrarHistorialMedico(String PesoKG, String Tamaño, String Edad, String Estado,
			String Alergias, String Tratamientos, String Cirugia, String HistorialMedico) {
		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			String query = "INSERT INTO HistorialMedico (PesoKG, Tamaño, Edad,"
					+ " Estado, Alergias, Tratamientos, Cirugia, FechaRegistro, IdAdopMascota) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_DATE,(SELECT IdAdopMascota FROM AdopMascota WHERE Nombre = ?))";
			PreparedStatement pst = conexion.prepareStatement(query);
			pst.setString(1, PesoKG);
			pst.setString(2, Tamaño);
			pst.setString(3, Edad);
			pst.setString(4, Estado);
			pst.setString(5, Alergias);
			pst.setString(6, Tratamientos);
			pst.setString(7, Cirugia);
			pst.setString(8, HistorialMedico);

			int resultado = pst.executeUpdate();

			return resultado > 0;
		} catch (SQLException e) {
			System.out.println("Error al Agregar Nuevo Historial: " + e);
			return false;
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
	}
	
	
	public List<String> obtenerAdopMascota() {
		List<String> AdopMascota = new ArrayList<>();
		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			String consulta = "SELECT Nombre FROM AdopMascota";
			PreparedStatement pst = conexion.prepareStatement(consulta);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				AdopMascota.add(rs.getString("Nombre"));
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener ID_AdopMascota: " + e);
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}

		return AdopMascota;
	}
	
	

	// ------------------------------------------------------------------
	public List<String> obtenerTiposUsuarios() {
		List<String> tipos_usuario = new ArrayList<>();
		conexion con = new conexion();
		Connection conexion = con.obtenerConexion();

		try {
			String consulta = "SELECT id_tipo_usuario FROM tipos_usuario";
			PreparedStatement pst = conexion.prepareStatement(consulta);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				tipos_usuario.add(rs.getString("id_tipo_usuario"));
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener tipos de usuario: " + e);
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				System.out.println("Error al cerrar la conexión: " + e);
			}
		}
		return tipos_usuario;
	}
	
	
	public boolean actualizarUsuario(int idUsuarios, String nombre, String apellido,
            String contrasena, String dni, String telefono, String cargo, String idTipoUsuario) {
        conexion con = new conexion();
        Connection conexion = con.obtenerConexion();

        try {
            String query = "UPDATE Usuarios SET Nombre=?, Apellido=?, Contraseña=?, DNI=?, Telefono=?, Cargo=?, id_tipo_usuario=? WHERE IdUsuarios=?";
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setString(3, contrasena);
            pst.setString(4, dni);
            pst.setString(5, telefono);
            pst.setString(6, cargo);
            pst.setString(7, idTipoUsuario);
            pst.setInt(8, idUsuarios);

            int resultado = pst.executeUpdate();

            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e);
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
    }
	
	
	
	public boolean eliminarUsuario(int idUsuario) {
	    conexion con = new conexion();
	    Connection conexion = con.obtenerConexion();

	    try {
	        String query = "DELETE FROM Usuarios WHERE IdUsuarios=?";
	        PreparedStatement pst = conexion.prepareStatement(query);
	        pst.setInt(1, idUsuario);

	        int resultado = pst.executeUpdate();

	        return resultado > 0;
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar el usuario: " + e);
	        return false;
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión: " + e);
	        }
	    }
	}
	
	
	public boolean actualizarProducto(int IdProductos, String Nombre, String Precio,
            String Cantidad, String Marca, String Descripcion, String FechaVencimiento, String FechaIngreso) {
        conexion con = new conexion();
        Connection conexion = con.obtenerConexion();

        try {
            String query = "UPDATE Productos SET Nombre=?, Precio=?,Cantidad=?, Marca=?, Descripcion=?, FechaVencimiento=?, FechaIngreso=? WHERE IdProductos=?";
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setString(1, Nombre);
            pst.setString(2, Precio);
            pst.setString(3, Cantidad);
            pst.setString(4, Marca);
            pst.setString(5, Descripcion);
            pst.setString(6, FechaVencimiento);
            pst.setString(7, FechaIngreso);
            pst.setInt(8, IdProductos);

            int resultado = pst.executeUpdate();

            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el Producto: " + e);
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
    }
	
	
	
	public boolean eliminarProducto(int IdProductos) {
	    conexion con = new conexion();
	    Connection conexion = con.obtenerConexion();

	    try {
	        String query = "DELETE FROM Productos WHERE IdProductos=?";
	        PreparedStatement pst = conexion.prepareStatement(query);
	        pst.setInt(1, IdProductos);

	        int resultado = pst.executeUpdate();

	        return resultado > 0;
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar el Producto: " + e);
	        return false;
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión: " + e);
	        }
	    }
	}
	
	
	
	public boolean actualizarCliente(int id_cliente, String Nombres, String Apellidos,
            String DNI, String Genero, String Direccion, String Telefono, String Correo) {
        conexion con = new conexion();
        Connection conexion = con.obtenerConexion();

        try {
            String query = "UPDATE Cliente SET Nombres=?, Apellidos=?,DNI=?, Genero=?, Direccion=?, Telefono=?, Correo=? WHERE id_cliente=?";
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setString(1, Nombres);
            pst.setString(2, Apellidos);
            pst.setString(3, DNI);
            pst.setString(4, Genero);
            pst.setString(5, Direccion);
            pst.setString(6, Telefono);
            pst.setString(7, Correo);
            pst.setInt(8, id_cliente);

            int resultado = pst.executeUpdate();

            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar Cliente: " + e);
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
    }
	
	
	
	public boolean eliminarCliente(int id_cliente) {
	    conexion con = new conexion();
	    Connection conexion = con.obtenerConexion();

	    try {
	        String query = "DELETE FROM Cliente WHERE id_cliente=?";
	        PreparedStatement pst = conexion.prepareStatement(query);
	        pst.setInt(1, id_cliente);

	        int resultado = pst.executeUpdate();

	        return resultado > 0;
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar Cliente: " + e);
	        return false;
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión: " + e);
	        }
	    }
	}
	
	
	
	
	public boolean actualizarMascota(int IdAdopMascota, String Nombre, String Raza,
            String Disponibilidad, String Pedigree, String FechaIngreso, String FechaAdopcion, String id_TipoMascota, String genero) {
        conexion con = new conexion();
        Connection conexion = con.obtenerConexion();

        try {
            String query = "UPDATE AdopMascota SET Nombre=?, Raza=?,Disponibilidad=?, Pedigree=?, FechaIngreso=?, FechaAdopcion=?, id_TipoMascota=? , Genero=? WHERE IdAdopMascota=?";
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setString(1, Nombre);
            pst.setString(2, Raza);
            pst.setString(3, Disponibilidad);
            pst.setString(4, Pedigree);
            pst.setString(5, FechaIngreso);
            pst.setString(6, FechaAdopcion);
            pst.setString(7, id_TipoMascota);
            pst.setString(8, genero);
            pst.setInt(9, IdAdopMascota);
            

            int resultado = pst.executeUpdate();

            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar Mascota: " + e);
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
    }
	
	
	
	public boolean eliminarMascota(int IdAdopMascota) {
	    conexion con = new conexion();
	    Connection conexion = con.obtenerConexion();

	    try {
	        String query = "DELETE FROM AdopMascota WHERE IdAdopMascota=?";
	        PreparedStatement pst = conexion.prepareStatement(query);
	        pst.setInt(1, IdAdopMascota);

	        int resultado = pst.executeUpdate();

	        return resultado > 0;
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar Mascota: " + e);
	        return false;
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión: " + e);
	        }
	    }
	}
	
	
	public boolean actualizarProveedor(int IdProveedor, String Nombre, String Apellido,
            String RUC, String RazonSocial, String Direccion, String Telefono, String Correo,String Categoria , String FechaCreacion) {
        conexion con = new conexion();
        Connection conexion = con.obtenerConexion();

        try {
            String query = "UPDATE Proveedor SET Nombre=?, Apellido=?,RUC=?, RazonSocial=?, Direccion=?, Telefono=?, Correo=? , Categoria=?, FechaCreacion=? WHERE IdProveedor=?";
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setString(1, Nombre);
            pst.setString(2, Apellido);
            pst.setString(3, RUC);
            pst.setString(4, RazonSocial);
            pst.setString(5, Direccion);
            pst.setString(6, Telefono);
            pst.setString(7, Correo);
            pst.setString(8, Categoria);
            pst.setString(9, FechaCreacion);
            pst.setInt(10, IdProveedor);

            int resultado = pst.executeUpdate();

            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar Proveedor: " + e);
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
    }
	
	
	
	public boolean eliminarProveedor(int IdProveedor) {
	    conexion con = new conexion();
	    Connection conexion = con.obtenerConexion();

	    try {
	        String query = "DELETE FROM Proveedor WHERE IdProveedor=?";
	        PreparedStatement pst = conexion.prepareStatement(query);
	        pst.setInt(1, IdProveedor);

	        int resultado = pst.executeUpdate();

	        return resultado > 0;
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar Proveedor: " + e);
	        return false;
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión: " + e);
	        }
	    }
	}
	
	
	public boolean actualizarHistorial(int IdHistorialMedico, String PesoKG, String Tamano,
            String Edad, String Estado, String Alergias, String Tratamientos, String Cirugia,String FechaRegistro , String IdAdopMascota) {
        conexion con = new conexion();
        Connection conexion = con.obtenerConexion();

        try {
            String query = "UPDATE HistorialMedico SET PesoKG=?, Tamaño=?,Edad=?, Estado=?, Alergias=?, Tratamientos=?, Cirugia=? , FechaRegistro=?, IdAdopMascota=? WHERE IdHistorialMedico=?";
            PreparedStatement pst = conexion.prepareStatement(query);
            pst.setString(1, PesoKG);
            pst.setString(2, Tamano);
            pst.setString(3, Edad);
            pst.setString(4, Estado);
            pst.setString(5, Alergias);
            pst.setString(6, Tratamientos);
            pst.setString(7, Cirugia);
            pst.setString(8, FechaRegistro);
            pst.setString(9, IdAdopMascota);
            pst.setInt(10, IdHistorialMedico);

            int resultado = pst.executeUpdate();

            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar Historial: " + e);
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
    }
	
	
	
	public boolean eliminarHistorial(int IdHistorialMedico) {
	    conexion con = new conexion();
	    Connection conexion = con.obtenerConexion();

	    try {
	        String query = "DELETE FROM HistorialMedico WHERE IdHistorialMedico=?";
	        PreparedStatement pst = conexion.prepareStatement(query);
	        pst.setInt(1, IdHistorialMedico);

	        int resultado = pst.executeUpdate();

	        return resultado > 0;
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar Historial: " + e);
	        return false;
	    } finally {
	        try {
	            if (conexion != null) {
	                conexion.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar la conexión: " + e);
	        }
	    }
	}
	
	
	
	
	
	
	

}