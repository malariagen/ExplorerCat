/**
 * This package contains the three types of exceptions we use in EC. Any custom
 * exception must extend one of these classes. I personally find the Java
 * checked exceptions system disgusting but I try to do some damage control
 * limiting them to their most local context (usually the package). For a
 * discussion about why checked exceptions are evil you can check chapter 7 of
 * Clean Code by Robert C. Martin or item 59 (Avoid unnecessary use of checked
 * exceptions) from Effective Java (second edition) by Joshua Bloch.
 * 
 * Checked Exceptions: For exceptions that are recoverable or can be controlled,
 * they have to extend ExplorerCatCheckedException.
 * 
 * Runtime Exceptions: For unrecoverable exceptions or exceptions that can not
 * be controlled (i.e. fatal errors), they have to extend
 * ExplorerCatCheckedExcption
 * 
 * Translation Exceptions: A type of runtime exception to be used when there is
 * an error performing a translation operation of any kind (here I mean
 * translation between two different representations, like a data file and a set
 * of DB tables). They have to use/extend TranslationException.
 * 
 * @author Jacob Almagro-Garcia
 */

package net.explorercat.util.exceptions;