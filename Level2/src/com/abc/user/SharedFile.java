package com.abc.user;

public class SharedFile {
	/** The read write. */
	private String fileName, ownerName, read = "", write = "", readWrite = "", fileId, fileDesc;

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
	 * Gets the owner name.
	 *
	 * @return the owner name
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * Sets the owner name.
	 *
	 * @param ownerName
	 *            the new owner name
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * Gets the read.
	 *
	 * @return the read
	 */
	public String getRead() {
		return read;
	}

	/**
	 * Sets the read.
	 *
	 * @param read
	 *            the new read
	 */
	public void setRead(String read) {
		this.read = read;
	}

	/**
	 * Gets the write.
	 *
	 * @return the write
	 */
	public String getWrite() {
		return write;
	}

	/**
	 * Sets the write.
	 *
	 * @param write
	 *            the new write
	 */
	public void setWrite(String write) {
		this.write = write;
	}

	/**
	 * Gets the read write.
	 *
	 * @return the read write
	 */
	public String getReadWrite() {
		return readWrite;
	}

	/**
	 * Sets the read write.
	 *
	 * @param readWrite
	 *            the new read write
	 */
	public void setReadWrite(String readWrite) {
		this.readWrite = readWrite;
	}
}