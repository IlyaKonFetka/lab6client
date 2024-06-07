package org.example.cmd.utils;

import org.example.TCP_components.Request;
import org.example.exceptions.ExecutionException;

import java.io.Serializable;

interface Executable {
    public Request apply(String userCommandArgument, boolean isScript) throws Ask.AskBreak, ExecutionException;
}
