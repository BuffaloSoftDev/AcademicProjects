/*
 * Name: George Rossney 
 * Partner: no partner 
 * Date submitted: 3/17/16 
 * Assignment: Bit Twiddling
 * Class: CSC 252 Spring 2016
 *
 * TA: Wolf Honore (whonore@u.rochester.edu)
 *
 * Instructions:
 * Fill in the following functions and turn this file in.
 *
 * Restrictions:
 * You MAY NOT change any of the function defintions.
 * You MAY NOT define any global variables, macros, or other functions.
 * You MAY NOT use any operations not listed in the function's description.
 * You MAY NOT use any control statements (if, loops, switch, function calls).
 * You MAY NOT use any constants outside of an unsigned byte (0 - 255 (0xFF) inclusive).
 * You MAY NOT cast any variables.
 * You MAY NOT use data types other than ints.
 *
 * You MAY define local variables.
 * You MAY use the assignment versions of allowed operators (+=, |=, etc).
 * You MAY use more than one operator per line.
 *
 * Your results will be tested on a machine that:
 * Uses 2s complement, 32-bit representations of integers.
 * Performs right shifts arithmetically.
 * Has unpredictable behavior when shifting an integer by more than the word size.
 *
 * If any of the instructions or function descriptions are unclear please contact the TA (see
 * above).
 */

/*
 * evenBits: Return the word with all even-numbered bits set to 1.
 *   Legal ops: ! ~ & ^ | + << >>
 */
int evenBits(void) {
    int size = 0x55;
    int words= size | (size<<8);
    return words | (words<<16); 
}

/*
 * bitAnd: Compute the bitwise-and of x and y.
 *   Example: bitAnd(3, 5) = 1
 *   Legal ops: ~ |
 */
int bitAnd(int x, int y) {
    return ~(~x|~y);
}

/*
 * swapBytes: Swap the 0th (rightmost) and 2nd bytes of x.
 *   Example: swapBytes(0x33221100) = 0x33001122
 *   Legal ops: ! ~ & ^ | + << >>
 */
int swapBytes(int x) {
    //0xFF is 255 (max)
    int bit=((0xFF) << 16)|(0xFF <<8 );
    int temp= bit & x; 
    int left= x<<28; 
    int right=(0xFF)&(x>>24);
    return (temp | left | right); 
}
/*
 * rotateLeft: Rotate x to the left by n bits. (For 0 <= n <= 31)
 *   Example: rotateLeft(0x76543210, 8) = 0x54321076
 *   Legal ops: ~ & ^ | + << >>
 */
int rotateLeft(int x, int n) {
    int rotate = ((1 << n) + 1 + ~1) << (33 + ~n);
    int z = ((x & rotate) >> (33 + ~n)) & ((1 << n) + 1 + ~1);
    return (x << n) | z;
}

/*
 * addOver: Determine if x + y overflows.
 *   Example: addOver(0x7FFFFFFE, 1) = 0
 *            addOver(0x7FFFFFFE, 2) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 */
int addOver(int x, int y) {
    int xy_sum = (x + y);
    int sign_x = (x >> 31);
    int sign_y = (y >> 31);
    int sign_sum_xy = (xy_sum >> 31);
    return !(~(sign_x ^ sign_y) & (sign_x ^ sign_sum_xy));
}

/*
 * boundedMult: Compute 2x, but return the largest positive or negative signed integer if overflow
 *              would occur.
 *   Example: boundedMult(0x00000001) = 0x00000002
 *            boundedMult(0x50000000) = 0x7FFFFFFF (bound to largest positive int)
 *            boundedMult(0x60000000) = 0x80000000 (bound to largest negative int)
 *   Legal ops: ! ~ & ^ | + << >>
 */
int boundedMult(int x, int n) {
    int min= (0x1<<31);
    int sign= x>>31;
    //adding x to itself, since mult is not legal 
    int product= (x+x); 
    int over=(x^product) >> 31; 
    int eval=over & (sign^(~min));
    return eval | (~over & product); 
    return 2; 
}

/*
 * absHalf: Compute |x / 2| rounding towards 0.
 *   Example: absaddHalf(3)  = 1
 *            absHalf(-6) = 3
 *   Legal ops: ! ~ & ^ | + << >>
 */
int absHalf(int x) {
    //shifting right by 1 to divide by 2 
    int result= x >> 1; 
    int z = result >>31; 
    return (z & -result) | (~z & result); 
}

/*
 * lessThan: Check if x < y.
 *   Example: lessThan(4, 5) = 1
 *            lessThan(5, 4) = 0
 *   Legal ops: ! ~ & ^ | + << >>
 */
int lessThan(int x, int y) {
    //cases for x < y
    int case1 = (x + (~y + 1));
    int case2 = (x & (~y));
    int case3 = ((~(x ^ y)) & case1);
    return (case2 | case3) >> 31 & 1;
}

/*
 * multFiveEighths: Compute 5/8ths of x rounding towards 0.
 *   Example: multFiveEighths(8)  = 5
 *            multFiveEighths(-9) = -5
 *   Legal ops: ! ~ & ^ | + << >>
 */
int multFiveEighths(int x) {
    int multFive = (x << 2) + x;
    // if multFive is negative, add 7 (2^3 - 1)
    int adder = 7 & (multFive >> 31);
    int divider = (multFive + adder) >> 3;
    return divider;
}

/*
 * isPwr2: Check if x is a power of 2. Assume x > 0.
 *   Example: isPwr2(2) = 1
 *            isPwr2(3) = 0
 *   Legal ops: ! ~ & ^ | + << >>
 */
int isPwr2(unsigned int x) {
    int minus_one= ~0;
    int sign_x= (x>>31);
    int minus=(minus_one^(sign_x));
    return !((x*(x+minus))+!x);
}
