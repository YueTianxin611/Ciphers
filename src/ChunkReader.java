package cipher;

import java.io.EOFException;
import java.io.IOException;

/** A ChunkReader reads bytes from an input stream in chunks of up to some fixed number of bytes. */
public interface ChunkReader {

	/**
	 * Returns the maximum number of bytes in a chunk.
	 */
    int chunkSize();

	/**
	 * Returns true if and only if there is at least one more byte of data to be
	 * read in the current stream.
	 */
    boolean hasNext();

    /**
	 * Returns the next chunk of up to {@code chunkSize()} bytes from the current
	 * input stream. The returned bytes are placed in the array {@code data},
	 * starting from index 0. The number of bytes returned is always
	 * {@code chunkSize()}, unless the end of the input stream has been reached and
	 * there are fewer than {@code chunkSize()} bytes available, in which case all
	 * remaining bytes are returned. The values in {@code data} after the region in
	 * which bytes were written are unspecified.
	 *
	 * @param data An array of length at least {@code chunkSize()}.
	 * @return The number of bytes returned, which is always between 1 and the chunk
	 *         size.
	 * @throws EOFException if there are no more bytes available.
	 * @throws IOException  if any IO exception occurs.
	 */
    int nextChunk(byte[] data) throws EOFException, IOException;
}
