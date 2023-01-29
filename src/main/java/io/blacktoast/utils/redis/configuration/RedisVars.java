package io.blacktoast.utils.redis.configuration;

/**
 * @author blacktoast
 *
 */
public class RedisVars {

	private String url = "localhost";
	private Integer port = 6379;
	private Integer maxPool = 0;
	private Integer popTimeOut = -1;

	public RedisVars() {
		super();
	}

	public RedisVars(String url, Integer port, Integer maxPool, Integer popTimeOut) {
		super();
		this.url = url;
		this.port = port;
		this.maxPool = maxPool;
		this.popTimeOut = popTimeOut;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getMaxPool() {
		return maxPool;
	}

	public void setMaxPool(Integer maxPool) {
		this.maxPool = maxPool;
	}

	public Integer getPopTimeOut() {
		return popTimeOut;
	}

	public void setPopTimeOut(Integer popTimeOut) {
		this.popTimeOut = popTimeOut;
	}

}
