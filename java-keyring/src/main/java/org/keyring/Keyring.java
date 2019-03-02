/*
 * Copyright © 2017, Rex Hoffman
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.keyring;

import org.keyring.util.LockException;

/**
 * Keyring.
 */
public class Keyring {

  /**
   * Keyring backend.
   */
  private KeyringBackend backend;
  
  /**
   * Creates an instance of Keyring using the a default backed based on operating system.
   * 
   * @return a functional Keyring or a BackendNotSupportedException is thrown.
   * @throws BackendNotSupportedException if the default backend for the operating system is unsupported.
   */
  public static Keyring create() throws BackendNotSupportedException {
    return new Keyring(KeyringBackendFactory.create());
  }

  /**
   * Creates an instance of Keyring with specified backend.
   *
   * @param keyring desired backend.
   * @return a functional Keyring or a BackendNotSupportedException is thrown.
   * @throws BackendNotSupportedException if the default backend for the operating system is unsupported.
   */
  public static Keyring create(Keyrings keyring) throws BackendNotSupportedException {
    return new Keyring(KeyringBackendFactory.create(keyring));
  }

  /**
   * Initializes an instance of Keyring.
   *
   * @param backend Keyring backend instance
   */
  private Keyring(KeyringBackend backend) {
    this.backend = backend;
  }

  /**
   * Gets path to key store (Proxy method of KeyringBackend.getKeyStorePath).
   * 
   * @return path to the keystore.
   */
  public String getKeyStorePath() {
    return backend.getKeyStorePath();
  }

  /**
   * Sets path to key store (Proxy method of KeyringBackend.setKeyStorePath).
   *
   * @param path Path to key store
   */
  public void setKeyStorePath(String path) {
    backend.setKeyStorePath(path);
  }

  /**
   * Returns true if the backend directory uses some file to store passwords.
   * (Proxy method of KeyringBackend.isKeyStorePathRequired)
   * 
   * @return if a path is required to store a password.
   */
  public boolean isKeyStorePathRequired() {
    return backend.isKeyStorePathRequired();
  }

  /**
   * Gets password from key store (Proxy method of KeyringBackend.getPassword).
   *
   * @param service
   *          Service name
   * @param account
   *          Account name
   *
   * @return Password related to specified service and account
   *
   * @throws PasswordRetrievalException
   *           Thrown when an error happened while getting password
   * @throws LockException
   *           can't establish lock.
   */
  public String getPassword(String service, String account) throws LockException, PasswordRetrievalException {
    return backend.getPassword(service, account);
  }

  /**
   * Sets password to key store (Proxy method of KeyringBackend.setPassword)
   *
   * @param service
   *          Service name
   * @param account
   *          Account name
   * @param password
   *          Password
   *
   * @throws PasswordSaveException
   *           Thrown when an error happened while saving the password
   * @throws LockException
   *           can't establish lock.
   */
  public void setPassword(String service, String account, String password) throws LockException, PasswordSaveException {
    backend.setPassword(service, account, password);
  }
  
  /**
   * Sets password to key store (Proxy method of KeyringBackend.setPassword)
   *
   * @param service
   *          Service name
   * @param account
   *          Account name
   *
   * @throws PasswordSaveException
   *           Thrown when an error happened while saving the password
   * @throws LockException
   *           can't establish lock.
   */
  public void deletePassword(String service, String account) throws LockException, PasswordSaveException {
    backend.deletePassword(service, account);
  }
}
