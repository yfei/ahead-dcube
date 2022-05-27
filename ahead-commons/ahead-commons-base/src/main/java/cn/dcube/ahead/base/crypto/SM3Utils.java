package cn.dcube.ahead.base.crypto;
/**
 * 国内SM3加密算法
 * @date: 2021-12-21 9:58 <br>
 * @author: yangfei <br>
 * @version: 1.0
 */
public class SM3Utils {
	/**
	 * IV为256比特初始值，32个字节，修饰为静态最终变量，不可改变，比如0x80值（128）范围超过byte表示范围（-128~127），所以需要强制转换
	 */
	private static final byte[] IV = { 0x73, (byte) 0x80, 0x16, 0x6f, 0x49, 0x14, (byte) 0xb2, (byte) 0xb9, 0x17, 0x24,
			0x42, (byte) 0xd7, (byte) 0xda, (byte) 0x8a, 0x06, 0x00, (byte) 0xa9, 0x6f, 0x30, (byte) 0xbc, (byte) 0x16,
			0x31, 0x38, (byte) 0xaa, (byte) 0xe3, (byte) 0x8d, (byte) 0xee, 0x4d, (byte) 0xb0, (byte) 0xfb, (byte) 0x0e,
			0x4e };

	/** 由于IV不可改变，而在64次迭代过程中需要一个不断改变的V，所以重新定义一个。 */
	private byte[] V = IV.clone();

	/** SM3分组长度 */
	private static final int BLOCK_LENGTH = 64;

	/** 缓冲区长度 */
	private static final int BUFFER_LENGTH = BLOCK_LENGTH * 2;

	/* 缓冲区偏移量 */
	private int xBufOff;

	/*
	 * 缓冲区，缓冲区长度是64,这个缓冲区指的什么？指的明文进入内存后，算法中要求需要将消息进行512比特分组,
	 * 然后对每组进行迭代，由于对消息原文是不能有改变的，所以需要将分组后的消息进行复制一份。
	 */
	private byte[] xBuf = new byte[BUFFER_LENGTH];

	private int cntBlock = 0;// 用于记录明文分组的个数,计算消息长度时需要用到。

	public static int[] Tj = new int[64];

	static {
		for (int i = 0; i < 16; i++) {
			Tj[i] = 0x79cc4519;
		}

		for (int i = 16; i < 64; i++) {
			Tj[i] = 0x7a879d8a;
		}
	}

	/*
	 * @param in明文输入缓冲区
	 * 
	 * @param inOff明文输入缓冲区偏移量
	 * 
	 * @param len明文输入长度
	 */
	public void update(byte[] in, int inOff) {

		int inputLen = in.length;// 明文的长度
		int dPos = inOff;// 明文输入缓冲区偏移量,一开始是0
		if (BUFFER_LENGTH < inputLen)// 如果缓冲区长度小于明文长度
		{
			System.arraycopy(in, dPos, xBuf, 0, BUFFER_LENGTH);
			inputLen = inputLen - BUFFER_LENGTH;// 明文输入长度减去进入缓冲区后剩下的长度
			dPos = dPos + BUFFER_LENGTH;
			doUpdate();
			while (inputLen > BUFFER_LENGTH) {
				System.arraycopy(in, dPos, xBuf, 0, BUFFER_LENGTH);
				inputLen = inputLen - BUFFER_LENGTH;
				dPos = dPos + BUFFER_LENGTH;
				doUpdate();
			}
		}
		if (inputLen > 0) {
			System.arraycopy(in, dPos, xBuf, 0, inputLen);
			xBufOff = inputLen;
		}
	}

	public void doUpdate()// 将缓冲区的内容复制到一份新的字节数组中。然后对字节数组作用。
	{
		byte[] B = new byte[BLOCK_LENGTH];
		for (int i = 0; i < BUFFER_LENGTH; i = i + BLOCK_LENGTH) {
			System.arraycopy(xBuf, i, B, 0, BLOCK_LENGTH);
			doHash(B);
		}
		xBufOff = 0;
	}

	private void doHash(byte[] B) {
		V = CF(V, B);
		// System.arraycopy(tmp,0,V,0,V.length);

		cntBlock++;
	}

	private byte[] CF(byte[] V, byte[] B) {
		int[] v, b;
		v = convert(V);
		b = convert(B);
		return convert(CF(v, b));
	}

	private int[] convert(byte[] arr) {
		int[] out = new int[arr.length / 4];
		byte[] tmp = new byte[4];
		for (int i = 0; i < arr.length; i += 4) {
			System.arraycopy(arr, i, tmp, 0, 4);
			out[i / 4] = bigEndianByteToInt(tmp);
		}
		return out;
	}

	private int bigEndianByteToInt(byte[] in) {
		int num = 0;
		int temp;
		temp = (0x000000ff & (in[3])) << 0;
		num = num | temp;
		temp = (0x000000ff & (in[2])) << 8;
		num = num | temp;
		temp = (0x000000ff & (in[1])) << 16;
		num = num | temp;
		temp = (0x000000ff & (in[0])) << 24;
		num = num | temp;
		return num;
	}

	public int[] CF(int[] v, int[] n) {
		int a, b, c, d, e, f, g, h;
		int ss1, ss2, tt1, tt2;
		a = v[0];
		b = v[1];
		c = v[2];
		d = v[3];
		e = v[4];
		f = v[5];
		g = v[6];
		h = v[7];
		int[][] arr = expand(n);
		int[] W = arr[0];
		int[] W1 = arr[1];
		for (int j = 0; j < 64; j++) {
			ss1 = bitCycleLeft(a, 12) + e + bitCycleLeft(Tj[j], j % 32);
			ss1 = bitCycleLeft(ss1, 7);
			ss2 = ss1 ^ bitCycleLeft(a, 12);
			tt1 = FFj(a, b, c, j) + d + ss2 + W1[j];
			tt2 = GGj(e, f, g, j) + h + ss1 + W[j];
			d = c;
			c = bitCycleLeft(b, 9);
			b = a;
			a = tt1;
			h = g;
			g = bitCycleLeft(f, 19);
			f = e;
			e = P0(tt2);
		}
		int[] out = new int[8];
		out[0] = a ^ v[0];
		out[1] = b ^ v[1];
		out[2] = c ^ v[2];
		out[3] = d ^ v[3];
		out[4] = e ^ v[4];
		out[5] = f ^ v[5];
		out[6] = g ^ v[6];
		out[7] = h ^ v[7];
		return out;
	}

	public int[][] expand(int[] b) {
		int[] W = new int[68];
		int[] W1 = new int[64];
		for (int i = 0; i < 16; i++) {
			W[i] = b[i];
		}
		for (int i = 16; i < 68; i++) {
			W[i] = P1(W[i - 16] ^ W[i - 9] ^ bitCycleLeft(W[i - 3], 15)) ^ bitCycleLeft(W[i - 13], 7) ^ W[i - 6];
		}
		for (int i = 0; i < 64; i++) {
			W1[i] = W[i] ^ W[i + 4];
		}
		int[][] arr = new int[][] { W, W1 };
		return arr;
	}

	public int bitCycleLeft(int target, int bitLen) {
		byte[] tmp = bigEndianIntToByte(target);
		int byteLen = bitLen / 8;
		int len = bitLen % 8;
		if (byteLen > 0) {
			tmp = byteCycleLeft(tmp, byteLen);
		}
		if (len > 0) {
			tmp = bitSmall8CycleLeft(tmp, len);
		}
		return bigEndianByteToInt(tmp);
	}

	public byte[] bigEndianIntToByte(int n) {
		byte[] tmp = new byte[4];
		tmp[3] = (byte) (n >> 0);
		tmp[2] = (byte) (n >> 8);
		tmp[1] = (byte) (n >> 16);
		tmp[0] = (byte) (n >> 24);
		return tmp;
	}

	public byte[] byteCycleLeft(byte[] in, int n) {
		byte[] tmp = new byte[in.length];
		System.arraycopy(in, n, tmp, 0, in.length - n);
		System.arraycopy(in, 0, tmp, in.length - n, n);
		return tmp;
	}

	public byte[] bitSmall8CycleLeft(byte[] in, int n) {
		byte[] tmp = new byte[in.length];
		int t1, t2, t3;
		for (int j = 0; j < tmp.length; j++) {
			t1 = (byte) ((in[j]) << n);
			t2 = (byte) (((in[(j + 1) % tmp.length]) & 0x000000ff) >> (8 - n));
			t3 = (byte) (t1 | t2);
			tmp[j] = (byte) t3;
		}
		return tmp;
	}

	public int FFj(int a, int b, int c, int j) {
		if (j >= 0 && j <= 15) {
			return a ^ b ^ c;
		} else {
			return (a & b) | (a & c) | (b & c);
		}
	}

	public int GGj(int a, int b, int c, int j) {
		if (j >= 0 && j <= 15) {
			return a ^ b ^ c;
		} else {
			return (a & b) | (~a & c);
		}
	}

	public int P0(int a) {
		int tmp = a ^ bitCycleLeft(a, 9) ^ bitCycleLeft(a, 17);
		return tmp;
	}

	public int P1(int a) {
		int tmp = a ^ bitCycleLeft(a, 15) ^ bitCycleLeft(a, 23);
		return tmp;
	}

	public byte[] convert(int[] v) {
		byte[] out = new byte[v.length * 4];
		int[] in = new int[v.length];
		for (int i = 0; i < v.length; i++) {
			System.arraycopy(v, 0, in, 0, v.length);
			out[4 * i + 3] = (byte) (in[i] >> 0);
			out[4 * i + 2] = (byte) (in[i] >> 8);
			out[4 * i + 1] = (byte) (in[i] >> 16);
			out[4 * i + 0] = (byte) (in[i] >> 24);
		}
		return out;
	}

	public byte[] doFinal() {
		byte[] B = new byte[BLOCK_LENGTH];
		byte[] buffer = new byte[xBufOff];
		System.arraycopy(xBuf, 0, buffer, 0, buffer.length);
		byte[] tmp = padding(buffer, cntBlock);
		for (int i = 0; i < tmp.length; i += 64)// 此处填充后tmp长度可能为64*2，也可能为64
		{
			System.arraycopy(tmp, i, B, 0, B.length);
			doHash(B);
		}

		return V;
	}

	/*
	 * @param in需要填充的最后一组字节数组
	 * 
	 * @param blen消息的分组次数 return 填充后的字节数组,注意当填充前数组为64字节时，填充后数组会比原来多一组。
	 */
	public byte[] padding(byte[] in, int blen) {
		long l = blen * BUFFER_LENGTH * 8 + in.length * 8;
		int k = 448 - (in.length * 8 + 8) % 512;
		if (k < 0) {
			k = 960 - (in.length * 8 + 8) % 512;
		}
		byte[] padd = new byte[k / 8 + 1];
		padd[0] = (byte) 0x80;
		byte[] out = new byte[in.length + k / 8 + 1 + 64 / 8];
		int pos = 0;
		System.arraycopy(in, 0, out, pos, in.length);
		pos = pos + in.length;
		System.arraycopy(padd, 0, out, pos, k / 8 + 1);
		pos = pos + k / 8 + 1;
		byte[] tmp = longToBytes(l);
		System.arraycopy(tmp, 0, out, pos, 64 / 8);
		return out;
	}

	public byte[] longToBytes(long n) {
		byte[] bytes = new byte[8];
		for (int i = 0; i < 8; i++) {
			bytes[7 - i] = (byte) (0xff & (n >> (i * 8)));
		}
		return bytes;
	}

	public String getHexString(byte[] bt) {
		String str = "";
		for (int i = 0; i < bt.length; i++) {

			str += String.format("%02x", bt[i] & 0xff);
			if (i % 4 == 3)
				str += "";
		}
		return str;
	}

	public static void main(String[] args) {
		byte[] md = new byte[32];
		byte[] msg1 = { 0x61, 0x62, 0x63 };
		SM3Utils sm3 = new SM3Utils();
		sm3.update(msg1, 0);
		md = sm3.doFinal();
		System.out.println("第一个例子的摘要值为" + sm3.getHexString(md));
		byte[] msg2 = "abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd".getBytes();
		SM3Utils sm3_1 = new SM3Utils();
		sm3_1.update(msg2, 0);
		md = sm3_1.doFinal();
		System.out.println("第二个例子的摘要值为" + sm3_1.getHexString(md).toUpperCase());
	}
}
