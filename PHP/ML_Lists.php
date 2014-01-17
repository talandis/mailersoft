<?php

    require_once dirname(__FILE__).'/base/ML_Rest.php';

    class ML_Lists extends ML_Rest
    {
        function ML_Lists( $api_key )
        {
            $this->name = 'lists';

            parent::__construct($api_key);
        }

        function getActive( $data = null )
        {
            return $this->execute( 'GET', $data, 'active' );
        }

        function getUnsubscribed( $data = null )
        {
            return $this->execute( 'GET', $data, 'unsubscribed' );
        }

        function getBounced( $data = null )
        {
            return $this->execute( 'GET', $data, 'bounced' );
        }
    }

?>