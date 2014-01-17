<?php

    require_once dirname(__FILE__).'/base/ML_Rest.php';

    class ML_Campaigns extends ML_Rest
    {
        function ML_Campaigns( $api_key )
        {
            $this->name = 'campaigns';

            parent::__construct($api_key);
        }

        function getRecipients( $data = null )
        {
            return $this->execute( 'GET', $data, 'recipients' );
        }

        function getOpens( $data = null )
        {
            return $this->execute( 'GET', $data, 'opens' );
        }

        function getClicks( $data = null )
        {
            return $this->execute( 'GET', $data, 'clicks' );
        }

        function getUnsubscribes( $data = null )
        {
            return $this->execute( 'GET', $data, 'unsubscribes' );
        }

        function getBounces( $data = null )
        {
            return $this->execute( 'GET', $data, 'bounces' );
        }

        function getJunk( $data = null )
        {
            return $this->execute( 'GET', $data, 'junks' );
        }
    }

?>