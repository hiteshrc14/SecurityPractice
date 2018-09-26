package com.abc.user;

public class File {
	/** The read write uses. */
	private String fileName, fileOwner, readUsers = "", writeUsers = "", readWriteUses = "", fileId, fileDesc;

	/**
	 * Gets the file desc.
	 *
	 * @return the file desc
	 */
	public String getFileDesc() {
		return fileDesc;
	}

	/**
	 * Sets the file desc.
	 *
	 * @param fileDesc
	 *            the new file desc
	 */
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	/**
	 * Gets the file id.
	 *
	 * @return the file id
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * Sets the file id.
	 *
	 * @param fileId
	 *            the new file id
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName
	 *            the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the file owner.
	 *
	 * @return the file owner
	 */
	public String getFileOwner() {
		return fileOwner;
	}

	/**
	 * Sets the file owner.
	 *
	 * @param fileOwner
	 *            the new file owner
	 */
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}

	/**
	 * Gets the read users.
	 *
	 * @return the read users
	 */
	public String getReadUsers() {
		return readUsers;
	}

	/**
	 * Sets the read users.
	 *
	 * @param readUsers
	 *            the new read users
	 */
	public void setReadUsers(String readUsers) {
		this.readUsers = readUsers;
	}

	/**
	 * Gets the write users.
	 *
	 * @return the write users
	 */
	public String getWriteUsers() {
		return writeUsers;
	}

	/**
	 * Sets the write users.
	 *
	 * @param writeUsers
	 *            the new write users
	 */
	public void setWriteUsers(String writeUsers) {
		this.writeUsers = writeUsers;
	}

	/**
	 * Gets the read write uses.
	 *
	 * @return the read write uses
	 */
	public String getReadWriteUses() {
		return readWriteUses;
	}

	/**
	 * Sets the read write uses.
	 *
	 * @param readWriteUses
	 *            the new read write uses
	 */
	public void setReadWriteUses(String readWriteUses) {
		this.readWriteUses = readWriteUses;
	}
}