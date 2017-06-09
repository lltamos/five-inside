package com.alqsoft.utils;
 
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alqsoft.init.InitParams;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
 
/**
 * @author Mr.hu
 * @version crateTime锛�2013-10-30 涓嬪崍5:41:30
 * Class Explain:JedisUtil 
 */
public class JedisUtils {
	    private static final Logger LOGGER = LoggerFactory.getLogger(JedisUtils.class);
	    private static JedisPool pool = null;

	    private static JedisUtils ru = new JedisUtils();
	   
	    public JedisUtils() {
	    	if (pool == null) {
	    		/*String ip = InitListener.getValue("redis.ip", "192.168.116.207");
	    		int port = Integer.parseInt(InitListener.getValue("redis.port", "5379"));*/
	            JedisPoolConfig config = new JedisPoolConfig();
	            // 鎺у埗涓�涓猵ool鍙垎閰嶅灏戜釜jedis瀹炰緥锛岄�氳繃pool.getResource()鏉ヨ幏鍙栵紱
	            // 濡傛灉璧嬪�间负-1锛屽垯琛ㄧず涓嶉檺鍒讹紱濡傛灉pool宸茬粡鍒嗛厤浜唌axActive涓猨edis瀹炰緥锛屽垯姝ゆ椂pool鐨勭姸鎬佷负exhausted(鑰楀敖)銆�
	            config.setMaxTotal(10000);
	            // 鎺у埗涓�涓猵ool鏈�澶氭湁澶氬皯涓姸鎬佷负idle(绌洪棽鐨�)鐨刯edis瀹炰緥銆�
	            config.setMaxIdle(2000);
	            // 琛ㄧず褰揵orrow(寮曞叆)涓�涓猨edis瀹炰緥鏃讹紝鏈�澶х殑绛夊緟鏃堕棿锛屽鏋滆秴杩囩瓑寰呮椂闂达紝鍒欑洿鎺ユ姏鍑篔edisConnectionException锛�
	            config.setMaxWaitMillis(1000 * 100);
	            config.setTestOnBorrow(true);
	            Integer port=Integer.valueOf(InitParams.redis_port);
	            
	            pool = new JedisPool(config, InitParams.redis_host, port, 100000);
	        }
	    	
		}

		/**
	     * <p>閫氳繃key鑾峰彇鍌ㄥ瓨鍦╮edis涓殑value</p>
	     * <p>骞堕噴鏀捐繛鎺�</p>
	     * @param key
	     * @return 鎴愬姛杩斿洖value 澶辫触杩斿洖null
	     */
	    public String get(String key){
	        Jedis jedis = null;
	        String value = null;
	        try {
	            jedis = pool.getResource();
	            value = jedis.get(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return value;
	    }

	    /**
	     * <p>鍚憆edis瀛樺叆key鍜寁alue,骞堕噴鏀捐繛鎺ヨ祫婧�</p>
	     * <p>濡傛灉key宸茬粡瀛樺湪 鍒欒鐩�</p>
	     * @param key
	     * @param value
	     * @return 鎴愬姛 杩斿洖OK 澶辫触杩斿洖 0
	     */
	    public String set(String key,String value){
	        Jedis jedis = null;
	        try {
	            jedis = pool.getResource();
	            return jedis.set(key, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	            return "0";
	        } finally {
	            returnResource(pool, jedis);
	        }
	    }


	    /**
	     * <p>鍒犻櫎鎸囧畾鐨刱ey,涔熷彲浠ヤ紶鍏ヤ竴涓寘鍚玨ey鐨勬暟缁�</p>
	     * @param keys 涓�涓猭ey  涔熷彲浠ヤ娇 string 鏁扮粍
	     * @return 杩斿洖鍒犻櫎鎴愬姛鐨勪釜鏁�
	     */
	    public Long del(String...keys){
	        Jedis jedis = null;
	        try {
	            jedis = pool.getResource();
	            return jedis.del(keys);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	            return 0L;
	        } finally {
	            returnResource(pool, jedis);
	        }
	    }

	    /**
	     * <p>閫氳繃key鍚戞寚瀹氱殑value鍊艰拷鍔犲��</p>
	     * @param key
	     * @param str
	     * @return 鎴愬姛杩斿洖 娣诲姞鍚巚alue鐨勯暱搴� 澶辫触 杩斿洖 娣诲姞鐨� value 鐨勯暱搴�  寮傚父杩斿洖0L
	     */
	    public Long append(String key ,String str){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.append(key, str);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	            return 0L;
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>鍒ゆ柇key鏄惁瀛樺湪</p>
	     * @param key
	     * @return true OR false
	     */
	    public Boolean exists(String key){
	        Jedis jedis = null;
	        try {
	            jedis = pool.getResource();
	            return jedis.exists(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	            return false;
	        } finally {
	            returnResource(pool, jedis);
	        }
	    }

	    /**
	     * <p>璁剧疆key value,濡傛灉key宸茬粡瀛樺湪鍒欒繑鍥�0,nx==> not exist</p>
	     * @param key
	     * @param value
	     * @return 鎴愬姛杩斿洖1 濡傛灉瀛樺湪 鍜� 鍙戠敓寮傚父 杩斿洖 0
	     */
	    public Long setnx(String key ,String value){
	        Jedis jedis = null;
	        try {
	            jedis = pool.getResource();
	            return jedis.setnx(key, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	            return 0L;
	        } finally {
	            returnResource(pool, jedis);
	        }
	    }

	    /**
	     * <p>璁剧疆key value骞跺埗瀹氳繖涓敭鍊肩殑鏈夋晥鏈�</p>
	     * @param key
	     * @param value
	     * @param seconds 鍗曚綅:绉�
	     * @return 鎴愬姛杩斿洖OK 澶辫触鍜屽紓甯歌繑鍥瀗ull
	     */
	    public String setex(String key,String value,int seconds){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.setex(key, seconds, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }


	    /**
	     * <p>閫氳繃key 鍜宱ffset 浠庢寚瀹氱殑浣嶇疆寮�濮嬪皢鍘熷厛value鏇挎崲</p>
	     * <p>涓嬫爣浠�0寮�濮�,offset琛ㄧず浠巓ffset涓嬫爣寮�濮嬫浛鎹�</p>
	     * <p>濡傛灉鏇挎崲鐨勫瓧绗︿覆闀垮害杩囧皬鍒欎細杩欐牱</p>
	     * <p>example:</p>
	     * <p>value : bigsea@zto.cn</p>
	     * <p>str : abc </p>
	     * <P>浠庝笅鏍�7寮�濮嬫浛鎹�  鍒欑粨鏋滀负</p>
	     * <p>RES : bigsea.abc.cn</p>
	     * @param key
	     * @param str
	     * @param offset 涓嬫爣浣嶇疆
	     * @return 杩斿洖鏇挎崲鍚�  value 鐨勯暱搴�
	     */
	    public Long setrange(String key,String str,int offset){
	        Jedis jedis = null;
	        try {
	            jedis = pool.getResource();
	            return jedis.setrange(key, offset, str);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	            return 0L;
	        } finally {
	            returnResource(pool, jedis);
	        }
	    }



	    /**
	     * <p>閫氳繃鎵归噺鐨刱ey鑾峰彇鎵归噺鐨剉alue</p>
	     * @param keys string鏁扮粍 涔熷彲浠ユ槸涓�涓猭ey
	     * @return 鎴愬姛杩斿洖value鐨勯泦鍚�, 澶辫触杩斿洖null鐨勯泦鍚� ,寮傚父杩斿洖绌�
	     */
	    public List<String> mget(String...keys){
	        Jedis jedis = null;
	        List<String> values = null;
	        try {
	            jedis = pool.getResource();
	            values = jedis.mget(keys);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return values;
	    }

	    /**
	     * <p>鎵归噺鐨勮缃甼ey:value,鍙互涓�涓�</p>
	     * <p>example:</p>
	     * <p>  obj.mset(new String[]{"key2","value1","key2","value2"})</p>
	     * @param keysvalues
	     * @return 鎴愬姛杩斿洖OK 澶辫触 寮傚父 杩斿洖 null
	     *
	     */
	    public String mset(String...keysvalues){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.mset(keysvalues);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>鎵归噺鐨勮缃甼ey:value,鍙互涓�涓�,濡傛灉key宸茬粡瀛樺湪鍒欎細澶辫触,鎿嶄綔浼氬洖婊�</p>
	     * <p>example:</p>
	     * <p>  obj.msetnx(new String[]{"key2","value1","key2","value2"})</p>
	     * @param keysvalues
	     * @return 鎴愬姛杩斿洖1 澶辫触杩斿洖0
	     */
	    public Long msetnx(String...keysvalues){
	        Jedis jedis = null;
	        Long res = 0L;
	        try {
	            jedis = pool.getResource();
	            res =jedis.msetnx(keysvalues);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>璁剧疆key鐨勫��,骞惰繑鍥炰竴涓棫鍊�</p>
	     * @param key
	     * @param value
	     * @return 鏃у�� 濡傛灉key涓嶅瓨鍦� 鍒欒繑鍥瀗ull
	     */
	    public String getset(String key,String value){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.getSet(key, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃涓嬫爣 鍜宬ey 鑾峰彇鎸囧畾涓嬫爣浣嶇疆鐨� value</p>
	     * @param key
	     * @param startOffset 寮�濮嬩綅缃� 浠�0 寮�濮� 璐熸暟琛ㄧず浠庡彸杈瑰紑濮嬫埅鍙�
	     * @param endOffset
	     * @return 濡傛灉娌℃湁杩斿洖null
	     */
	    public String getrange(String key, int startOffset ,int endOffset){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.getrange(key, startOffset, endOffset);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key 瀵箆alue杩涜鍔犲��+1鎿嶄綔,褰搗alue涓嶆槸int绫诲瀷鏃朵細杩斿洖閿欒,褰搆ey涓嶅瓨鍦ㄦ槸鍒檝alue涓�1</p>
	     * @param key
	     * @return 鍔犲�煎悗鐨勭粨鏋�
	     */
	    public Long incr(String key){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.incr(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key缁欐寚瀹氱殑value鍔犲��,濡傛灉key涓嶅瓨鍦�,鍒欒繖鏄痸alue涓鸿鍊�</p>
	     * @param key
	     * @param integer
	     * @return
	     */
	    public Long incrBy(String key,Long integer){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.incrBy(key, integer);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>瀵筴ey鐨勫�煎仛鍑忓噺鎿嶄綔,濡傛灉key涓嶅瓨鍦�,鍒欒缃甼ey涓�-1</p>
	     * @param key
	     * @return
	     */
	    public Long decr(String key) {
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.decr(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>鍑忓幓鎸囧畾鐨勫��</p>
	     * @param key
	     * @param integer
	     * @return
	     */
	    public Long decrBy(String key,Long integer){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.decrBy(key, integer);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇value鍊肩殑闀垮害</p>
	     * @param key
	     * @return 澶辫触杩斿洖null
	     */
	    public Long serlen(String key){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.strlen(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key缁檉ield璁剧疆鎸囧畾鐨勫��,濡傛灉key涓嶅瓨鍦�,鍒欏厛鍒涘缓</p>
	     * @param key
	     * @param field 瀛楁
	     * @param value
	     * @return 濡傛灉瀛樺湪杩斿洖0 寮傚父杩斿洖null
	     */
	    public Long hset(String key,String field,String value) {
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hset(key, field, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key缁檉ield璁剧疆鎸囧畾鐨勫��,濡傛灉key涓嶅瓨鍦ㄥ垯鍏堝垱寤�,濡傛灉field宸茬粡瀛樺湪,杩斿洖0</p>
	     * @param key
	     * @param field
	     * @param value
	     * @return
	     */
	    public Long hsetnx(String key,String field,String value){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hsetnx(key, field, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍚屾椂璁剧疆 hash鐨勫涓猣ield</p>
	     * @param key
	     * @param hash
	     * @return 杩斿洖OK 寮傚父杩斿洖null
	     */
	    public String hmset(String key,Map<String, String> hash){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hmset(key, hash);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key 鍜� field 鑾峰彇鎸囧畾鐨� value</p>
	     * @param key
	     * @param field
	     * @return 娌℃湁杩斿洖null
	     */
	    public String hget(String key, String field){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hget(key, field);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key 鍜� fields 鑾峰彇鎸囧畾鐨剉alue 濡傛灉娌℃湁瀵瑰簲鐨剉alue鍒欒繑鍥瀗ull</p>
	     * @param key
	     * @param fields 鍙互浣� 涓�涓猄tring 涔熷彲浠ユ槸 String鏁扮粍
	     * @return
	     */
	    public List<String> hmget(String key,String...fields){
	        Jedis jedis = null;
	        List<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hmget(key, fields);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key缁欐寚瀹氱殑field鐨剉alue鍔犱笂缁欏畾鐨勫��</p>
	     * @param key
	     * @param field
	     * @param value
	     * @return
	     */
	    public Long hincrby(String key ,String field ,Long value){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hincrBy(key, field, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍜宖ield鍒ゆ柇鏄惁鏈夋寚瀹氱殑value瀛樺湪</p>
	     * @param key
	     * @param field
	     * @return
	     */
	    public Boolean hexists(String key , String field){
	        Jedis jedis = null;
	        Boolean res = false;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hexists(key, field);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖field鐨勬暟閲�</p>
	     * @param key
	     * @return
	     */
	    public Long hlen(String key){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hlen(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;

	    }

	    /**
	     * <p>閫氳繃key 鍒犻櫎鎸囧畾鐨� field </p>
	     * @param key
	     * @param fields 鍙互鏄� 涓�涓� field 涔熷彲浠ユ槸 涓�涓暟缁�
	     * @return
	     */
	    public Long hdel(String key ,String...fields){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hdel(key, fields);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖鎵�鏈夌殑field</p>
	     * @param key
	     * @return
	     */
	    public Set<String> hkeys(String key){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hkeys(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖鎵�鏈夊拰key鏈夊叧鐨剉alue</p>
	     * @param key
	     * @return
	     */
	    public List<String> hvals(String key){
	        Jedis jedis = null;
	        List<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hvals(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇鎵�鏈夌殑field鍜寁alue</p>
	     * @param key
	     * @return
	     */
	    public Map<String, String> hgetall(String key){
	        Jedis jedis = null;
	        Map<String, String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.hgetAll(key);
	        } catch (Exception e) {
	            // TODO
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍚憀ist澶撮儴娣诲姞瀛楃涓�</p>
	     * @param key
	     * @param strs 鍙互浣夸竴涓猻tring 涔熷彲浠ヤ娇string鏁扮粍
	     * @return 杩斿洖list鐨剉alue涓暟
	     */
	    public Long lpush(String key ,String...strs){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.lpush(key, strs);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }
	    
	    
	    /**
	     * <p>閫氳繃key鍚憀ist灏鹃儴娣诲姞瀛楃涓�</p>
	     * @param key
	     * @param strs 鍙互浣夸竴涓猻tring 涔熷彲浠ヤ娇string鏁扮粍
	     * @return 杩斿洖list鐨剉alue涓暟
	     */
	    public Long rpush(String key ,String...strs){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.rpush(key, strs);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍦╨ist鎸囧畾鐨勪綅缃箣鍓嶆垨鑰呬箣鍚� 娣诲姞瀛楃涓插厓绱�</p>
	     * @param key
	     * @param where LIST_POSITION鏋氫妇绫诲瀷
	     * @param pivot list閲岄潰鐨剉alue
	     * @param value 娣诲姞鐨剉alue
	     * @return
	     */
	    public Long linsert(String key, LIST_POSITION where,
	                        String pivot, String value){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.linsert(key, where, pivot, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key璁剧疆list鎸囧畾涓嬫爣浣嶇疆鐨剉alue</p>
	     * <p>濡傛灉涓嬫爣瓒呰繃list閲岄潰value鐨勪釜鏁板垯鎶ラ敊</p>
	     * @param key
	     * @param index 浠�0寮�濮�
	     * @param value
	     * @return 鎴愬姛杩斿洖OK
	     */
	    public String lset(String key ,Long index, String value){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.lset(key, index, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key浠庡搴旂殑list涓垹闄ゆ寚瀹氱殑count涓� 鍜� value鐩稿悓鐨勫厓绱�</p>
	     * @param key
	     * @param count 褰揷ount涓�0鏃跺垹闄ゅ叏閮�
	     * @param value
	     * @return 杩斿洖琚垹闄ょ殑涓暟
	     */
	    public Long lrem(String key,long count,String value){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.lrem(key, count, value);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key淇濈暀list涓粠strat涓嬫爣寮�濮嬪埌end涓嬫爣缁撴潫鐨剉alue鍊�</p>
	     * @param key
	     * @param start
	     * @param end
	     * @return 鎴愬姛杩斿洖OK
	     */
	    public String ltrim(String key ,long start ,long end){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.ltrim(key, start, end);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key浠巐ist鐨勫ご閮ㄥ垹闄や竴涓獀alue,骞惰繑鍥炶value</p>
	     * @param key
	     * @return
	     */
	    synchronized public String lpop(String key){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.lpop(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key浠巐ist灏鹃儴鍒犻櫎涓�涓獀alue,骞惰繑鍥炶鍏冪礌</p>
	     * @param key
	     * @return
	     */
	    synchronized public String rpop(String key){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.rpop(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key浠庝竴涓猯ist鐨勫熬閮ㄥ垹闄や竴涓獀alue骞舵坊鍔犲埌鍙︿竴涓猯ist鐨勫ご閮�,骞惰繑鍥炶value</p>
	     * <p>濡傛灉绗竴涓猯ist涓虹┖鎴栬�呬笉瀛樺湪鍒欒繑鍥瀗ull</p>
	     * @param srckey
	     * @param dstkey
	     * @return
	     */
	    public String rpoplpush(String srckey, String dstkey){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.rpoplpush(srckey, dstkey);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇list涓寚瀹氫笅鏍囦綅缃殑value</p>
	     * @param key
	     * @param index
	     * @return 濡傛灉娌℃湁杩斿洖null
	     */
	    public String lindex(String key,long index){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.lindex(key, index);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖list鐨勯暱搴�</p>
	     * @param key
	     * @return
	     */
	    public Long llen(String key){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.llen(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇list鎸囧畾涓嬫爣浣嶇疆鐨剉alue</p>
	     * <p>濡傛灉start 涓� 0 end 涓� -1 鍒欒繑鍥炲叏閮ㄧ殑list涓殑value</p>
	     * @param key
	     * @param start
	     * @param end
	     * @return
	     */
	    public List<String> lrange(String key,long start,long end){
	        Jedis jedis = null;
	        List<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.lrange(key, start, end);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍚戞寚瀹氱殑set涓坊鍔爒alue</p>
	     * @param key
	     * @param members 鍙互鏄竴涓猄tring 涔熷彲浠ユ槸涓�涓猄tring鏁扮粍
	     * @return 娣诲姞鎴愬姛鐨勪釜鏁�
	     */
	    public Long sadd(String key,String...members){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.sadd(key, members);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍒犻櫎set涓搴旂殑value鍊�</p>
	     * @param key
	     * @param members 鍙互鏄竴涓猄tring 涔熷彲浠ユ槸涓�涓猄tring鏁扮粍
	     * @return 鍒犻櫎鐨勪釜鏁�
	     */
	    public Long srem(String key,String...members){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.srem(key, members);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key闅忔満鍒犻櫎涓�涓猻et涓殑value骞惰繑鍥炶鍊�</p>
	     * @param key
	     * @return
	     */
	    public String spop(String key){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.spop(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇set涓殑宸泦</p>
	     * <p>浠ョ涓�涓猻et涓烘爣鍑�</p>
	     * @param keys 鍙互浣夸竴涓猻tring 鍒欒繑鍥瀞et涓墍鏈夌殑value 涔熷彲浠ユ槸string鏁扮粍
	     * @return
	     */
	    public Set<String> sdiff(String...keys){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.sdiff(keys);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇set涓殑宸泦骞跺瓨鍏ュ埌鍙︿竴涓猭ey涓�</p>
	     * <p>浠ョ涓�涓猻et涓烘爣鍑�</p>
	     * @param dstkey 宸泦瀛樺叆鐨刱ey
	     * @param keys 鍙互浣夸竴涓猻tring 鍒欒繑鍥瀞et涓墍鏈夌殑value 涔熷彲浠ユ槸string鏁扮粍
	     * @return
	     */
	    public Long sdiffstore(String dstkey,String... keys){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.sdiffstore(dstkey, keys);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇鎸囧畾set涓殑浜ら泦</p>
	     * @param keys 鍙互浣夸竴涓猻tring 涔熷彲浠ユ槸涓�涓猻tring鏁扮粍
	     * @return
	     */
	    public Set<String> sinter(String...keys){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.sinter(keys);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇鎸囧畾set涓殑浜ら泦 骞跺皢缁撴灉瀛樺叆鏂扮殑set涓�</p>
	     * @param dstkey
	     * @param keys 鍙互浣夸竴涓猻tring 涔熷彲浠ユ槸涓�涓猻tring鏁扮粍
	     * @return
	     */
	    public Long sinterstore(String dstkey,String...keys){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.sinterstore(dstkey, keys);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖鎵�鏈塻et鐨勫苟闆�</p>
	     * @param keys 鍙互浣夸竴涓猻tring 涔熷彲浠ユ槸涓�涓猻tring鏁扮粍
	     * @return
	     */
	    public Set<String> sunion(String... keys){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.sunion(keys);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖鎵�鏈塻et鐨勫苟闆�,骞跺瓨鍏ュ埌鏂扮殑set涓�</p>
	     * @param dstkey
	     * @param keys 鍙互浣夸竴涓猻tring 涔熷彲浠ユ槸涓�涓猻tring鏁扮粍
	     * @return
	     */
	    public Long sunionstore(String dstkey,String...keys){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.sunionstore(dstkey, keys);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key灏唖et涓殑value绉婚櫎骞舵坊鍔犲埌绗簩涓猻et涓�</p>
	     * @param srckey 闇�瑕佺Щ闄ょ殑
	     * @param dstkey 娣诲姞鐨�
	     * @param member set涓殑value
	     * @return
	     */
	    public Long smove(String srckey, String dstkey, String member){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.smove(srckey, dstkey, member);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇set涓璿alue鐨勪釜鏁�</p>
	     * @param key
	     * @return
	     */
	    public Long scard(String key){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.scard(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍒ゆ柇value鏄惁鏄痵et涓殑鍏冪礌</p>
	     * @param key
	     * @param member
	     * @return
	     */
	    public Boolean sismember(String key,String member){
	        Jedis jedis = null;
	        Boolean res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.sismember(key, member);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇set涓殢鏈虹殑value,涓嶅垹闄ゅ厓绱�</p>
	     * @param key
	     * @return
	     */
	    public String srandmember(String key){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.srandmember(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇set涓墍鏈夌殑value</p>
	     * @param key
	     * @return
	     */
	    public Set<String> smembers(String key){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.smembers(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }


	    /**
	     * <p>閫氳繃key鍚憐set涓坊鍔爒alue,score,鍏朵腑score灏辨槸鐢ㄦ潵鎺掑簭鐨�</p>
	     * <p>濡傛灉璇alue宸茬粡瀛樺湪鍒欐牴鎹畇core鏇存柊鍏冪礌</p>
	     * @param key
	     * @param score
	     * @param member
	     * @return
	     */
	    public Long zadd(String key,double score,String member){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zadd(key, score, member);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍒犻櫎鍦▃set涓寚瀹氱殑value</p>
	     * @param key
	     * @param members 鍙互浣夸竴涓猻tring 涔熷彲浠ユ槸涓�涓猻tring鏁扮粍
	     * @return
	     */
	    public Long zrem(String key,String...members){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zrem(key, members);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key澧炲姞璇set涓璿alue鐨剆core鐨勫��</p>
	     * @param key
	     * @param score
	     * @param member
	     * @return
	     */
	    public Double zincrby(String key ,double score ,String member){
	        Jedis jedis = null;
	        Double res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zincrby(key, score, member);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖zset涓璿alue鐨勬帓鍚�</p>
	     * <p>涓嬫爣浠庡皬鍒板ぇ鎺掑簭</p>
	     * @param key
	     * @param member
	     * @return
	     */
	    public Long zrank(String key,String member){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zrank(key, member);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖zset涓璿alue鐨勬帓鍚�</p>
	     * <p>涓嬫爣浠庡ぇ鍒板皬鎺掑簭</p>
	     * @param key
	     * @param member
	     * @return
	     */
	    public Long zrevrank(String key,String member){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zrevrank(key, member);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key灏嗚幏鍙杝core浠巗tart鍒癳nd涓瓃set鐨剉alue</p>
	     * <p>socre浠庡ぇ鍒板皬鎺掑簭</p>
	     * <p>褰搒tart涓�0 end涓�-1鏃惰繑鍥炲叏閮�</p>
	     * @param key
	     * @param start
	     * @param end
	     * @return
	     */
	    public Set<String> zrevrange(String key ,long start ,long end){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zrevrange(key, start, end);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖鎸囧畾score鍐厇set涓殑value</p>
	     * @param key
	     * @param max
	     * @param min
	     * @return
	     */
	    public Set<String> zrangebyscore(String key,String max,String min){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zrevrangeByScore(key, max, min);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖鎸囧畾score鍐厇set涓殑value</p>
	     * @param key
	     * @param max
	     * @param min
	     * @return
	     */
	    public Set<String> zrangeByScore(String key ,double max,double min){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zrevrangeByScore(key,max,min);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>杩斿洖鎸囧畾鍖洪棿鍐厇set涓璿alue鐨勬暟閲�</p>
	     * @param key
	     * @param min
	     * @param max
	     * @return
	     */
	    public Long zcount(String key,String min,String max){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zcount(key, min, max);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key杩斿洖zset涓殑value涓暟</p>
	     * @param key
	     * @return
	     */
	    public Long zcard(String key){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zcard(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鑾峰彇zset涓璿alue鐨剆core鍊�</p>
	     * @param key
	     * @param member
	     * @return
	     */
	    public Double zscore(String key,String member){
	        Jedis jedis = null;
	        Double res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zscore(key, member);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍒犻櫎缁欏畾鍖洪棿鍐呯殑鍏冪礌</p>
	     * @param key
	     * @param start
	     * @param end
	     * @return
	     */
	    public Long zremrangeByRank(String key ,long start, long end){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zremrangeByRank(key, start, end);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍒犻櫎鎸囧畾score鍐呯殑鍏冪礌</p>
	     * @param key
	     * @param start
	     * @param end
	     * @return
	     */
	    public Long zremrangeByScore(String key,double start,double end){
	        Jedis jedis = null;
	        Long res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.zremrangeByScore(key, start, end);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }
	    /**
	     * <p>杩斿洖婊¤冻pattern琛ㄨ揪寮忕殑鎵�鏈塳ey</p>
	     * <p>keys(*)</p>
	     * <p>杩斿洖鎵�鏈夌殑key</p>
	     * @param pattern
	     * @return
	     */
	    public Set<String> keys(String pattern){
	        Jedis jedis = null;
	        Set<String> res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.keys(pattern);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * <p>閫氳繃key鍒ゆ柇鍊煎緱绫诲瀷</p>
	     * @param key
	     * @return
	     */
	    public String type(String key){
	        Jedis jedis = null;
	        String res = null;
	        try {
	            jedis = pool.getResource();
	            res = jedis.type(key);
	        } catch (Exception e) {
	            
	            LOGGER.error(e.getMessage());
	        } finally {
	            returnResource(pool, jedis);
	        }
	        return res;
	    }

	    /**
	     * 杩旇繕鍒拌繛鎺ユ睜
	     *
	     * @param pool
	     * @param jedis
	     */
	    public static void returnResource(JedisPool pool, Jedis jedis) {
	        if (jedis != null) {
	            pool.returnResource(jedis);
	        }
	    }

		public static JedisUtils getRu() {
			return ru;
		}

		public static void setRu(JedisUtils ru) {
			JedisUtils.ru = ru;
		}
	}
      
     
