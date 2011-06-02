/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.mapreduce.jobhistory;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.mapreduce.JobID;

import org.apache.avro.util.Utf8;

/**
 * Event to record the initialization of a job
 *
 */
@InterfaceAudience.Private
@InterfaceStability.Unstable
public class JobInitedEvent implements HistoryEvent {
  private JobInited datum = new JobInited();

  /**
   * Create an event to record job initialization
   * @param id
   * @param launchTime
   * @param totalMaps
   * @param totalReduces
   * @param jobStatus
   */
  public JobInitedEvent(JobID id, long launchTime, int totalMaps,
                        int totalReduces, boolean isUber, int numUberSubMaps,
                        int numUberSubReduces, String jobStatus) {
    datum.jobid = new Utf8(id.toString());
    datum.launchTime = launchTime;
    datum.totalMaps = totalMaps;
    datum.totalReduces = totalReduces;
    datum.isUber = isUber;
    datum.numUberSubMaps = numUberSubMaps;
    datum.numUberSubReduces = numUberSubReduces;
    datum.jobStatus = new Utf8(jobStatus);
  }

  JobInitedEvent() { }

  public Object getDatum() { return datum; }
  public void setDatum(Object datum) { this.datum = (JobInited)datum; }

  /** Get the job ID */
  public JobID getJobId() { return JobID.forName(datum.jobid.toString()); }
  /** Get the launch time */
  public long getLaunchTime() { return datum.launchTime; }
  /** Get the total number of "real" maps */
  public int getTotalMaps() { return datum.totalMaps; }
  /** Get the total number of "real" reduces */
  public int getTotalReduces() { return datum.totalReduces; }
  /** Was the job small enough to be converted to an UberTask? */
  public boolean getIsUber() { return datum.isUber; }
  /** Get the number of sub-MapTasks within the UberTask */
  public int getNumUberSubMaps() { return datum.numUberSubMaps; }
  /** Get the number of sub-ReduceTasks within the UberTask */
  public int getNumUberSubReduces() { return datum.numUberSubReduces; }
  /** Get the status */
  public String getStatus() { return datum.jobStatus.toString(); }
 /** Get the event type */
  public EventType getEventType() {
    return EventType.JOB_INITED;
  }

}