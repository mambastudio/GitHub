/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrapper.core.buffer;

import java.nio.ByteBuffer;
import org.jocl.Pointer;
import org.jocl.cl_mem;
import wrapper.core.CCommandQueue;
import wrapper.core.CMemory;
import wrapper.core.CallBackArray;

/**
 *
 * @author user
 * @param <B>
 */
public class CStructTypeBuffer <B> extends CMemory<ByteBuffer> {
    private final B[] structs;
    
    public CStructTypeBuffer(cl_mem memory, B[] structs, ByteBuffer buffer, Pointer pointer, long cl_size) {
        super(memory, buffer, pointer, cl_size);
        this.structs = structs;
    }
    
    public B[] mapReadBuffer(CCommandQueue queue, CallBackArray<B> function)
    {
        queue.putReadBuffer(this);
        function.call(structs);
        return structs;
    }
    
    public B[] mapWriteBuffer(CCommandQueue queue, CallBackArray<B> function)
    { 
        function.call(structs);
        queue.putWriteBuffer(this);
        return structs;
    }
    
    public B get(int i)
    {        
        return structs[i];
    }
    
    public B[] getStructArray()
    {
        return structs;
    }
    
    public int getSize()
    {
        return structs.length;
    }   
}
