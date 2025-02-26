/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2021, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/

package schemacrawler.crawl;

import static java.util.Comparator.naturalOrder;
import static us.fatehi.utility.Utility.isBlank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import schemacrawler.schema.JdbcDriverInfo;
import schemacrawler.schema.JdbcDriverProperty;

/**
 * JDBC driver information. Created from metadata returned by a JDBC call, and other sources of
 * information.
 *
 * @author Sualeh Fatehi sualeh@hotmail.com
 */
final class MutableJdbcDriverInfo implements JdbcDriverInfo {

  private static final long serialVersionUID = 8030156654422512161L;

  private final Set<ImmutableJdbcDriverProperty> jdbcDriverProperties;
  private String connectionUrl;
  private String driverClassName;
  private String driverName;
  private String driverVersion;
  private int driverMajorVersion;
  private int driverMinorVersion;
  private int jdbcMajorVersion;
  private int jdbcMinorVersion;
  private boolean jdbcCompliant;

  public MutableJdbcDriverInfo() {

    jdbcDriverProperties = new HashSet<>();
    connectionUrl = "";
    driverClassName = "";
    driverName = "";
    driverVersion = "";
    driverMajorVersion = 0;
    driverMinorVersion = 0;
    jdbcMajorVersion = 0;
    jdbcMinorVersion = 0;
    jdbcCompliant = false;
  }

  /** {@inheritDoc} */
  @Override
  public String getConnectionUrl() {
    return connectionUrl;
  }

  /** {@inheritDoc} */
  @Override
  public String getDriverClassName() {
    return driverClassName;
  }

  @Override
  public int getDriverMajorVersion() {
    return driverMajorVersion;
  }

  @Override
  public int getDriverMinorVersion() {
    return driverMinorVersion;
  }

  /** {@inheritDoc} */
  @Override
  public Collection<JdbcDriverProperty> getDriverProperties() {
    final List<JdbcDriverProperty> properties = new ArrayList<>(jdbcDriverProperties);
    properties.sort(naturalOrder());
    return properties;
  }

  @Override
  public int getJdbcMajorVersion() {
    return jdbcMajorVersion;
  }

  @Override
  public int getJdbcMinorVersion() {
    return jdbcMinorVersion;
  }

  /** {@inheritDoc} */
  @Override
  public String getProductName() {
    return driverName;
  }

  /** {@inheritDoc} */
  @Override
  public String getProductVersion() {
    return driverVersion;
  }

  @Override
  public boolean hasDriverClassName() {
    return !isBlank(driverClassName);
  }

  /** {@inheritDoc} */
  @Override
  public boolean isJdbcCompliant() {
    return jdbcCompliant;
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    final StringBuilder info = new StringBuilder(1024);
    info.append("-- driver: ")
        .append(getProductName())
        .append(' ')
        .append(getProductVersion())
        .append(System.lineSeparator());
    info.append("-- driver class: ").append(getDriverClassName()).append(System.lineSeparator());
    info.append("-- url: ").append(getConnectionUrl()).append(System.lineSeparator());
    info.append("-- jdbc compliant: ").append(isJdbcCompliant());
    return info.toString();
  }

  /**
   * Adds a JDBC driver property.
   *
   * @param jdbcDriverProperty JDBC driver property
   */
  void addJdbcDriverProperty(final ImmutableJdbcDriverProperty jdbcDriverProperty) {
    jdbcDriverProperties.add(jdbcDriverProperty);
  }

  void setConnectionUrl(final String connectionUrl) {
    this.connectionUrl = connectionUrl;
  }

  void setDriverMajorVersion(final int driverMajorVersion) {
    this.driverMajorVersion = driverMajorVersion;
  }

  void setDriverMinorVersion(final int driverMinorVersion) {
    this.driverMinorVersion = driverMinorVersion;
  }

  void setDriverName(final String driverName) {
    this.driverName = driverName;
  }

  void setDriverVersion(final String driverVersion) {
    this.driverVersion = driverVersion;
  }

  void setJdbcCompliant(final boolean jdbcCompliant) {
    this.jdbcCompliant = jdbcCompliant;
  }

  void setJdbcDriverClassName(final String jdbcDriverClassName) {
    driverClassName = jdbcDriverClassName;
  }

  void setJdbcMajorVersion(final int jdbcMajorVersion) {
    this.jdbcMajorVersion = jdbcMajorVersion;
  }

  void setJdbcMinorVersion(final int jdbcMinorVersion) {
    this.jdbcMinorVersion = jdbcMinorVersion;
  }
}
