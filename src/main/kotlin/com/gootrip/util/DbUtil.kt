package com.gootrip.util

import org.apache.commons.dbcp2.BasicDataSource
import org.apache.commons.dbutils.DbUtils
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import java.io.Closeable
import java.sql.*
import java.util.*


/**
 * 连接数据库的综合类。
 * 依赖jar包：commons.dbcp-1.4，commons.dbutils-1.3，commons.pool-1.5.4包。
 *
 * @author 余晓伟
 * @date 2017年02月04日
 */
class DbUtil(
    val name: String,  //只读属性，生成一个字段和一个简单的get方法
    var isMarried: Boolean   //可写属性，一个字段，一个get，一个set
)
    : Closeable {
    private var ds: BasicDataSource = BasicDataSource()
    private var conn: Connection
    private var st: Statement

    init {
        val cfg = Properties()
        //读取db.properties文件
        this::class.java.classLoader.getResourceAsStream("db.properties").use {
            cfg.load(it)
            //初始化参数
            this.ds.driverClassName = cfg.getProperty("jdbc.driver");
            this.ds.url = cfg.getProperty("jdbc.url");
            this.ds.username = cfg.getProperty("jdbc.username");
            this.ds.password = cfg.getProperty("jdbc.password");
            this.ds.initialSize = cfg.getProperty("initSize").toInt()
            this.ds.maxTotal = cfg.getProperty("maxTotal").toInt()
        }

        /*
         * getConnection()从连接池中获取的重用
         * 连接，如果连接池满了，则等待。
         * 如果有归还的连接线，则获取重用的连接
         */
        this.conn = ds.connection
        this.st = conn.createStatement()
    }

    /**
     * 关闭数据库的连接方法，封装复杂的关闭过程
     */
    override fun close() {
        this.conn.close()
    }

    /**
     * 查询sql语句。
     * @param sql 被执行的sql语句
     * @return List<Map></Map><String></String>,Object>>
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun query(sql: String?): List<Map<String?, Any?>?>? {
        val qr = QueryRunner()
        return qr.query(this.conn, sql, MapListHandler())
    }

    /**
     * 根据参数查询sql语句
     * @param sql sql语句
     * @param param 参数
     * @return List<Map></Map><String></String>,Object>>
     * @throws SQLException
     */
    @Throws(SQLException::class)
    fun query(sql: String?, param: Any?): List<Map<String?, Any?>?>? {
        val qr = QueryRunner()

        return qr.query(conn, sql, MapListHandler(), param) as List<Map<String?, Any?>?>
    }

    /**
     * 执行sql语句
     * @param sql 被执行的sql语句
     * @return 受影响的行
     * @throws Exception
     */
    @Throws(Exception::class)
    fun execute(sql: String?): Int {
        val qr = QueryRunner()

        return qr.update(conn, sql)
    }

    /**
     * 执行含参数的sql语句
     * @param sql 被执行的sql语句
     * @param params 参数
     * @return 返回受影响的行
     * @throws Exception
     */
    @Throws(Exception::class)
    fun execute(sql: String?, params: Array<Any?>): Int {
        val qr = QueryRunner()
        var rows = qr.update(conn, sql, *params)
        return rows
    }
}