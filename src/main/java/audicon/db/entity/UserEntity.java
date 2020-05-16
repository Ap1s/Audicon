package audicon.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User.java
 * This class maps to a table in database.
 *
 */

@Entity
@Table(name = "user")
public class UserEntity {

	private int id;
	private String username; 
	private String password;
	public UserEntity() {

	}
	
	
	
	public UserEntity(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}



	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
