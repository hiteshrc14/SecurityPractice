package com.abc.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class FileManager.
 * 
 */
public class FileManager {

	/** The connection. */
	private Connection connection = null;

	/** The ps. */
	private PreparedStatement preparedStatement = null;

	/**
	 * Instantiates a new user manager.
	 */
	public FileManager() {
		connection = DBConnection.getInstance();
	}

	/**
	 * Gets the file list.
	 *
	 * @param userName
	 *            the user name
	 * @return the file list
	 */
	public List<File> getFileList(String userName) {
		List<File> files = new ArrayList<File>();
		UserManager userManager = new UserManager();
		try {
			preparedStatement = null;
			// get file details for owner
			preparedStatement = connection.prepareStatement("select id,filename from upload where owner_username=?");
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String fileName = rs.getString("filename");
				String fileId = rs.getString("id");
				File file = new File();
				file.setFileId(fileId);
				file.setFileName(fileName);
				file.setFileOwner(userManager.getFullName(userName));
				// is user is a owner of file
				PreparedStatement ps = connection
						.prepareStatement("select * from shared where owner_username=? and file_id=?");
				ps.setString(1, userName);
				ps.setString(2, fileId);
				ResultSet rs2 = ps.executeQuery();
				StringBuilder readString = new StringBuilder();
				StringBuilder writeString = new StringBuilder();
				StringBuilder readWriteString = new StringBuilder();
				while (rs2.next()) {
					boolean read = rs2.getBoolean("read_access");
					boolean write = rs2.getBoolean("write_access");
					String sharedUsername = rs2.getString("shared_username");
					if (read && !write) {
						readString.append(userManager.getFullName(sharedUsername) + ", ");
					}
					if (!read && write) {
						writeString.append(userManager.getFullName(sharedUsername) + ", ");
					}
					if (read && write) {
						readWriteString.append(userManager.getFullName(sharedUsername) + ", ");
					}
				}
				// get all read users
				if (readString.length() > 2) {
					file.setReadUsers(readString.substring(0, readString.length() - 2));
				}
				// get all write users
				if (writeString.length() > 2) {
					file.setWriteUsers(writeString.substring(0, writeString.length() - 2));
				}
				// get read/write users
				if (readWriteString.length() > 2) {
					file.setReadWriteUses(readWriteString.substring(0, readWriteString.length() - 2));
				}
				files.add(file);
			}
		} catch (Exception e) {
		}
		return files;
	}

	/**
	 * Gets the file shared with me.
	 *
	 * @param userName
	 *            the user name
	 * @return the file shared with me
	 */
	public List<SharedFile> getFileSharedWithMe(String userName) {
		List<SharedFile> sharedFiles = new ArrayList<SharedFile>();
		UserManager userManager = new UserManager();
		try {
			preparedStatement = null;
			// which files shared with given username
			preparedStatement = connection.prepareStatement(
					"select owner_username,file_id,read_access,write_access from shared where shared_username=?");
			preparedStatement.setString(1, userName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ownerUsername = rs.getString("owner_username");
				String file_id = rs.getString("file_id");
				boolean read_access = rs.getBoolean("read_access");
				boolean write_access = rs.getBoolean("write_access");
				SharedFile sharedFile = new SharedFile();
				sharedFile.setFileId(file_id);
				// get that files description
				PreparedStatement ps = connection
						.prepareStatement("select filename,description from upload where id=?");
				ps.setString(1, file_id);
				ResultSet rs2 = ps.executeQuery();
				// store file description
				if (rs2.next()) {
					sharedFile.setFileName(rs2.getString("filename"));
					sharedFile.setFileDesc(rs2.getString("description"));
				} else {
					sharedFile.setFileName("Name Not Found");
				}
				sharedFile.setOwnerName(userManager.getFullName(ownerUsername));
				if (read_access && !write_access) {
					sharedFile.setRead("True");
				} else {
					sharedFile.setRead("False");
				}
				if (!read_access && write_access) {
					sharedFile.setWrite("True");
				} else {
					sharedFile.setWrite("False");
				}
				if (read_access && write_access) {
					sharedFile.setReadWrite("True");
				} else {
					sharedFile.setReadWrite("False");
				}
				sharedFiles.add(sharedFile);
			}
		} catch (Exception e) {
		}
		return sharedFiles;
	}
	public void close()
	{
		DBConnection.closeDBConnection();
	}
}