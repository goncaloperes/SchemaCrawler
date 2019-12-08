SELECT /*+ PARALLEL(AUTO) */
  NULL AS TABLE_CATALOG,
  TABLES.OWNER AS TABLE_SCHEMA,
  TABLES.TABLE_NAME,
  DBMS_METADATA.GET_DDL('TABLE', TABLES.TABLE_NAME, TABLES.OWNER)
    AS TABLE_DEFINITION
FROM
  ${catalogscope}_TABLES TABLES
  INNER JOIN ${catalogscope}_TAB_COMMENTS TAB_COMMENTS
    ON TABLES.OWNER = TAB_COMMENTS.OWNER
       AND TABLES.TABLE_NAME = TAB_COMMENTS.TABLE_NAME
WHERE
  TABLES.OWNER NOT IN
    ('ANONYMOUS', 'APEX_PUBLIC_USER', 'APPQOSSYS', 'BI', 'CTXSYS', 'DBSNMP', 'DIP',
    'EXFSYS', 'FLOWS_30000', 'FLOWS_FILES', 'GSMADMIN_INTERNAL', 'IX', 'LBACSYS',
    'MDDATA', 'MDSYS', 'MGMT_VIEW', 'OE', 'OLAPSYS', 'ORACLE_OCM',
    'ORDPLUGINS', 'ORDSYS', 'OUTLN', 'OWBSYS', 'PM', 'SCOTT', 'SH',
    'SI_INFORMTN_SCHEMA', 'SPATIAL_CSW_ADMIN_USR', 'SPATIAL_WFS_ADMIN_USR',
    'SYS', 'SYSMAN', 'SYSTEM', 'TSMSYS', 'WKPROXY', 'WKSYS', 'WK_TEST',
    'WMSYS', 'XDB', 'XS$NULL', 'RDSADMIN')
  AND NOT REGEXP_LIKE(TABLES.OWNER, '^APEX_[0-9]{6}$')
  AND NOT REGEXP_LIKE(TABLES.OWNER, '^FLOWS_[0-9]{5}$')
  AND REGEXP_LIKE(TABLES.OWNER, '${schemas}')
  AND TABLES.TABLE_NAME NOT LIKE 'BIN$%'
  AND NOT REGEXP_LIKE(TABLES.TABLE_NAME, '^(SYS_IOT|MDOS|MDRS|MDRT|MDOT|MDXT)_.*$')
  AND TABLES.NESTED = 'NO'
  AND (TABLES.IOT_TYPE IS NULL OR TABLES.IOT_TYPE = 'IOT')
ORDER BY
  TABLE_SCHEMA,
  TABLE_NAME
