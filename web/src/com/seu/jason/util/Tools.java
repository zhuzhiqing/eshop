package com.seu.jason.util;

import java.util.Random;

/**
 * Created by ToZhu on 2015/10/29.
 */
public class Tools {

    static Random rnd = new Random();
    static int seed = 0;

    public static String generatToken()
    {
//        byte[] rndData = new byte[4];
//        rnd.nextInt(rndData);
//
//        var seedValue = Interlocked.Add(ref seed, 1);
//        var seedData = BitConverter.GetBytes(seedValue);
//
//        var tokenData = rndData.Concat(seedData).OrderBy(_ => rnd.Next());
//        return Convert.ToBase64String(tokenData.ToArray()).TrimEnd('=');


        return "abcbkjhaslkhlnmhklka";
    }



    }
